<template>
<nav class="navbar" style="background-color:#0742F2;">
    <div class="container-fluid">
        <img src="../assets/logo.png" alt="" style="width:150px">
        <!-- 나가기 -->
        <button class="btn" style="color:white; background-color:#F2CB05;" data-bs-toggle="modal" data-bs-target="#Consultclose">상담종료</button>
    </div>
</nav>
<div class="container">
    <h1>1대1상담</h1>
    <div style="margin-left : 3px; margin-right : 3px;">
        <div class="row">
            <div class="col-8">
                <div class="row justify-content-center mx-3">
                    <video id="videoOutput" autoplay class="col-8 my-3" style="display : inline"
                    ref="videoOutput"></video>
                    <video id="videoInput" autoplay class="col-8 my-3" style="display : inline" muted="true"
                    ref="videoInput"></video>
                </div>
                <div ref="editorMenu" style="display : none">
                    <button ref="btnBold">
                        <b>B</b>
                    </button>
                    <select ref="fontSize">
                        <option value="">폰트 사이즈</option>
                        <option value="1">10px</option>
                        <option value="2">13px</option>
                        <option value="3">16px</option>
                        <option value="4">18px</option>
                        <option value="5">24px</option>
                        <option value="6">32px</option>
                        <option value="7">48px</option>
                    </select>
                    <select ref="fontColor">
                        <option value="">색상</option>
                        <option value="#000000">검정</option>
                        <option value="#FFFFFF">흰색</option>
                        <option value="#CCCCCC">회색</option>
                        <option value="#F03E3E">빨강</option>
                        <option value="#1971C2">파랑</option>
                        <option value="#37B24D">녹색</option>
                    </select>
                    <select ref="fontBackground">
                        <option value="rgba(0, 0, 0, 0)">배경색상</option>
                        <option value="#000000">검정</option>
                        <option value="#FFFFFF">흰색</option>
                        <option value="#CCCCCC">회색</option>
                        <option value="#F03E3E">빨강</option>
                        <option value="#1971C2">파랑</option>
                        <option value="#37B24D">녹색</option>
                    </select>
                </div>
                <div class="row" ref="script" contenteditable="true" style="display : inline; outline : none">
                    Who is your favorite actor or actress?
                    Describe a particular story about something this person did which you heard about in the news.
                    Begin with some details about the actor or actress and then describe all the details of what occurred,
                    in particular, tell me about thethings that made this experience so memorable to people who like movies.
                </div>
                <div>
                    <div>
                        <button @click.prevent="scriptControl" ref="scriptButton" style="display:none">Script ON</button>
                    </div>
                    <button @click.prevent="muteControl" ref="sound">음소거</button>
                    <button @click.prevent="screenControl" ref="screen">비디오 중지</button>
                </div>
            </div>

            <div ref="chat-page" class="hidden col-4">
                <div class="chat-container">
                    <div class="connecting">
                    </div>
                    <ul ref="messageArea">
                    </ul>
                    <form @submit.prevent="sendMessage" ref="messageForm" name="messageForm">
                        <div class="form-group">
                            <div class="input-group clearfix">
                                <input type="text" ref="messageInput" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                                <button type="submit" class="primary">보내기</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<ConsultCloseModal></ConsultCloseModal>
</template>
<script>
import ConsultCloseModal from '@/components/Modal/ConsultCloseModal.vue';
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

const configuration = {
    iceServers: [
        {
            url: 'turn:3.34.51.116:3478',
            username : 'myuser',
            credential : 'mypassword'
        },
        {
            url : 'stun:stun.l.google.com:19302'
        }
    ]
}

var constraints = {
    video : {
        frameRate : {
            ideal : 10,
            max : 15
        },
        width : 1280,
        height : 720,
        facingMode : "user"
    },

    audio : true,
};

var peerConnection = new RTCPeerConnection(configuration);
var dataChannel = peerConnection.createDataChannel("dataChannel", { reliable: true });
var stompClient = null;

export default {
    mounted() {
        const fontSizeSelector = this.$refs.fontSize;
        const selectFontColor = this.$refs.fontColor
        const selectFontBackground = this.$refs.fontBackground
        var copy = this.$refs.copy
        var messageArea = this.$refs.messageArea;
        var username = this.$store.state.auth.user.username;
        var room = 'wow';
        var videoInput = this.$refs.videoInput
        var videoOutput = this.$refs.videoOutput
        var script = this.$refs.script
        let btnBold = this.$refs.btnBold
        let role = this.$store.state.auth.user.role
        let inboundStream = null;
        let scriptButton = this.$refs.scriptButton
        let editorMenu = this.$refs.editorMenu
            
        dataChannel.onerror = function(error) {
            console.log("Error:", error);
        };
        dataChannel.onclose = function() {
            console.log("Data channel is closed");
        };
        
        selectFontColor.addEventListener('change', function () {
            setFontColor(this.value);
        });
        
        selectFontBackground.addEventListener('change', function () {
            setFontBackground(this.value);
        });
        
        function setFontColor(color) {
            document.execCommand('foreColor', false, color);
            focusEditor();
        }

        function setFontBackground(color) {
            document.execCommand('hiliteColor', false, color);
            focusEditor();
        }

        fontSizeSelector.addEventListener('change', function () {
            changeFontSize(this.value);
        });

        function changeFontSize(size) {
            document.execCommand('fontSize', false, size);
            focusEditor();
        }

        function setStyle(style) {
            document.execCommand(style);
            focusEditor();
            checkStyle();
        }

        function checkStyle() {
            if (isStyle('bold')) {
                btnBold.classList.add('active');
            } else {
                btnBold.classList.remove('active');
            }
        }

        function isStyle(style) {
            return document.queryCommandState(style);
        }

        function focusEditor() {
            script.focus({preventScroll: true});
        }

        script.addEventListener('keydown', function () {
            checkStyle();
        });

        script.addEventListener('mousedown', function () {
            checkStyle();
        });

        btnBold.addEventListener('click', function () {
            setStyle('bold');
        });

        function onError(error) {
            console.log('Could not connect to WebSocket server. Please refresh this page to try again!');
        }

        function onMessageReceived(payload) {
            var message = JSON.parse(payload.body);
            if(message.type === 'JOIN') {
                var messageElement = document.createElement('li');
                messageElement.classList.add('event-message');
                message.content = message.sender + ' 님이 입장하셨습니다.';
            } else if (message.type === 'LEAVE') {
                var messageElement = document.createElement('li');
                messageElement.classList.add('event-message');
                message.content = message.sender + ' 님이 퇴장하셨습니다.';
            } else if (message.type === 'OFFER') {
                if (message.sender != username){
                    peerConnection.setRemoteDescription(new RTCSessionDescription({
                        sdp : message.content.sdp,
                        type : message.content.type
                    }));
                    peerConnection.createAnswer().then( function(answer) {
                        peerConnection.setLocalDescription({
                            sdp : answer.sdp,
                            type : answer.type
                            });
                        stompClient.send('/topic/public/' + room,
                        JSON.stringify({
                            type : "ANSWER",
                            content : answer,
                            sender : username
                        }),
                        {}
                        );
                    });

                    peerConnection.onicecandidate = function(event) {
                        if (event.candidate) {
                            stompClient.send('/topic/public/' + room,
                            JSON.stringify({
                                type : "CANDIDATE",
                                content : event.candidate,
                                sender : username
                            }),
                            {});
                        }
                    };
                }

            } else if (message.type === 'CANDIDATE') {
                if (message.sender != username){
                    peerConnection.addIceCandidate({
                        candidate : message.content.candidate,
                        sdpMid : message.content.sdpMid,
                        sdpMLineIndex : message.content.sdpMLineIndex
                    })
                }
            } else if (message.type === 'ANSWER') {
                peerConnection.setRemoteDescription(new RTCSessionDescription({
                    sdp : message.content.sdp,
                    type : message.content.type
                    }));
            } else {
                copy.innerHTML = message.content
            }
            if (message.type === 'JOIN'|| message.type === 'LEAVE'){
                var textElement = document.createElement('p');
                var messageText = document.createTextNode(message.content);
                textElement.appendChild(messageText);

                messageElement.appendChild(textElement);

                messageArea.appendChild(messageElement);
                messageArea.scrollTop = messageArea.scrollHeight;
            }
        }

        peerConnection.ondatachannel = (event) => {
            dataChannel = event.channel;
        };

        dataChannel.onmessage = function (event) {
            if (event.data === '비디오 시작' || event.data === '비디오 중지'
            || event.data === '음소거' || event.data === '음소거 해제'
            || event.data === '스크립트 시작' || event.data === '스크립트 중지'){
                if (event.data === '음소거'){
                    videoOutput.srcObject.getAudioTracks()[0].enabled = false
                } else if (event.data === '음소거 해제'){
                    videoOutput.srcObject.getAudioTracks()[0].enabled = true
                } else if (event.data === '비디오 시작'){
                    videoOutput.removeAttribute('style')
                } else if (event.data === '스크립트 시작'){
                    script.removeAttribute("style")
                    scriptButton.innerText = "Script OFF"
                    videoInput.classList.remove("col-8")
                    videoInput.classList.add("col-6")
                    videoOutput.classList.remove("col-8")
                    videoOutput.classList.add("col-6")
                } else if (event.data === '스크립트 중지'){
                    script.setAttribute("style", "display : none")
                    scriptButton.innerText = "Script ON"
                    videoInput.classList.remove("col-6")
                    videoInput.classList.add("col-8")
                    videoOutput.classList.remove("col-6")
                    videoOutput.classList.add("col-8")
                } else {
                    videoOutput.setAttribute("style","display:none")
                } 
            }else {
                var data = JSON.parse(event.data)
                if (data.username != username){
                    script.innerHTML = data.message
                } else {
                    var messageElement = document.createElement('li');
                    messageElement.classList.add('chat-message');

                    var usernameElement = document.createElement('span');
                    var usernameText = document.createTextNode(data.username);
                    usernameElement.appendChild(usernameText);
                    messageElement.appendChild(usernameElement);

                    if (data.message != null){
                        var textElement = document.createElement('p');
                        var messageText = document.createTextNode(data.message);
                        textElement.appendChild(messageText);

                        messageElement.appendChild(textElement);

                        messageArea.appendChild(messageElement);
                        messageArea.scrollTop = messageArea.scrollHeight;
                    }
                }                
            }
        };

        peerConnection.addEventListener('connectionstatechange', event => {
            if (peerConnection.connectionState === 'connected') {
                console.log("Peers connected!") 
            }
        });
        
        async function openCall() {
            let inputStream = new MediaStream();
            videoInput.srcObject = inputStream;
            const gumStream = await navigator.mediaDevices.getUserMedia(
                                constraints);
            for (const track of gumStream.getTracks()) {
                peerConnection.addTrack(track);
                inputStream.addTrack(track);
            }

            if (role === 'consultant') {
                scriptButton.removeAttribute("style")
                script.setAttribute("style", "display : inline")
                script.setAttribute("contenteditable", "true")
                editorMenu.removeAttribute("style")
            }
            if(username) {
                var socket = new SockJS('https://localhost:5443/ws');
                stompClient = Stomp.over(socket);
                stompClient.connect({}, () => {
                    stompClient.subscribe('/topic/public/' + room, onMessageReceived);
                    stompClient.send("/app/chat.addUser",
                        JSON.stringify({sender: username, type: 'JOIN', content: room},
                        {})
                    )
                    peerConnection.createOffer().then((offer) => {
                        stompClient.send('/topic/public/' + room,
                        JSON.stringify({
                            type : "OFFER",
                            content : offer,
                            sender : username
                        }),
                        {});

                        peerConnection.setLocalDescription(offer);

                    })

                    peerConnection.onicecandidate = function(event) {
                        if (event.candidate) {
                            stompClient.send('/topic/public/' + room,
                            JSON.stringify({
                                type : "CANDIDATE",
                                content : event.candidate,
                                sender : username
                            }),
                            {});
                        }
                    };
                }, onError);
            }
        }
        
        peerConnection.ontrack = (event) => {
            if (event.streams && event.streams[0]) {
                videoOutput.srcObject = event.streams[0];
            } else {
                if (!inboundStream) {
                    inboundStream = new MediaStream();
                    videoOutput.srcObject = inboundStream;
                }
                inboundStream.addTrack(event.track);
            }
        };

        openCall()
        
        setInterval(() => {
            var pre = this.$refs.script.innerHTML
            var post = this.$refs.script.innerHTML            
            if (pre != post){
                dataChannel.send(JSON.stringify({
                    "username" : this.$store.state.auth.user.username,
                    "message" : post
                }))
            }
            }, 100)

    },
    computed: {
        currentUser() {
            return this.$store.state.auth.user;
        },
    },
    methods: {
        scriptControl(){
            if (scriptButton.innerText === 'Script ON'){
                dataChannel.send("스크립트 시작")
                script.removeAttribute("style")
                this.$refs.videoInput.classList.remove("col-8")
                this.$refs.videoInput.classList.add("col-6")
                this.$refs.videoOutput.classList.remove("col-8")
                this.$refs.videoOutput.classList.add("col-6")
                this.$refs.scriptButton.innerText = "Script OFF"
            }else {
                this.$refs.script.setAttribute("style","display : none")
                dataChannel.send("스크립트 중지")
                this.$refs.videoInput.classList.remove("col-6")
                this.$refs.videoInput.classList.add("col-8")
                this.$refs.videoOutput.classList.remove("col-6")
                this.$refs.videoOutput.classList.add("col-8")
                this.$refs.scriptButton.innerText = "Script ON"
            }
        },
        screenControl(){
            if (this.$refs.screen.innerText === '비디오 중지'){
                this.$refs.videoInput.setAttribute("style","display:none")
                dataChannel.send("비디오 중지")
                this.$refs.screen.innerText = "비디오 시작"
            }else {
                this.$refs.videoInput.setAttribute("style","display:inline")
                dataChannel.send("비디오 시작")
                this.$refs.screen.innerText = "비디오 중지"
            }
        },
        muteControl(){
            if (this.$refs.sound.innerText === '음소거'){
                dataChannel.send("음소거")
                this.$refs.sound.innerText = "음소거 해제"
            }else {
                dataChannel.send("음소거 해제")
                this.$refs.sound.innerText = "음소거"
            }
        },

        sendMessage() {
            var messageContent = this.$refs.messageInput.value.trim();
            if(messageContent && stompClient) {
                console.log(dataChannel)
                dataChannel.send(JSON.stringify({
                    "username" : this.$store.state.auth.user.username,
                    "message" : messageContent
                }))

                var messageElement = document.createElement('li');
                messageElement.classList.add('chat-message');

                var usernameElement = document.createElement('span');
                var usernameText = document.createTextNode(this.$store.state.auth.user.username);
                usernameElement.appendChild(usernameText);
                messageElement.appendChild(usernameElement);

                var textElement = document.createElement('p');
                var messageText = document.createTextNode(messageContent);
                textElement.appendChild(messageText);

                messageElement.appendChild(textElement);

                this.$refs.messageArea.appendChild(messageElement);
                this.$refs.messageArea.scrollTop = this.$refs.messageArea.scrollHeight;
                this.$refs.messageInput.value = '';
            }
        }
    },
    components: { ConsultCloseModal }
};

</script>