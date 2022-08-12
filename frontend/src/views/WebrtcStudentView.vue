<template>
<nav class="navbar" style="background-color:#0742F2;">
    <div class="container-fluid">
        <img src="../assets/logo.png" alt="" style="width:150px">
        <!-- 나가기 -->
        <button class="btn" style="color:white; background-color:#F2CB05;" data-bs-toggle="modal" data-bs-target="#Consultclose">상담종료</button>
    </div>
</nav>
<div>
    <div style="margin-left : 3px;">
        <div class="row">
            <div class="col-8">
                <div class="row mx-3">
                    <div class="outer col-8 my-3" ref="outputOuter">
                        <video id="videoOutput" autoplay style="display : inline;"
                        ref="videoOutput"></video>
                    </div>
                    <div class="outer col-8 my-3" ref="inputOuter">
                        <video id="videoInput" autoplay style="display : inline;" muted="true"
                        ref="videoInput"></video>
                    </div>
                </div>
                <div class="row justify-content-between my-2 mx-3">
                    <button class="col-3 button" @click.prevent="scriptControl" ref="scriptButton" style="display:none">Script ON</button>
                    <div class="col-7">
                        <div class="row justify-content-end" ref="editorMenu" style="display : none">
                            <select class="col-2 mx-1" ref="fontSize">
                                <option value="">크기</option>
                                <option value="1">10px</option>
                                <option value="2">13px</option>
                                <option value="3">16px</option>
                                <option value="4">18px</option>
                                <option value="5">24px</option>
                                <option value="6">32px</option>
                                <option value="7">48px</option>
                            </select>
                            <select class="col-2 mx-1" ref="fontColor">
                                <option value="">색상</option>
                                <option value="#000000">검정</option>
                                <option value="#FFFFFF">흰색</option>
                                <option value="#CCCCCC">회색</option>
                                <option value="#F03E3E">빨강</option>
                                <option value="#1971C2">파랑</option>
                                <option value="#37B24D">녹색</option>
                            </select>
                            <select class="col-2 mx-1" ref="fontBackground">
                                <option value="rgba(0, 0, 0, 0)">배경</option>
                                <option value="#000000">검정</option>
                                <option value="#FFFFFF">흰색</option>
                                <option value="#CCCCCC">회색</option>
                                <option value="#F03E3E">빨강</option>
                                <option value="#1971C2">파랑</option>
                                <option value="#37B24D">녹색</option>
                            </select>
                            <button class="col-2 ms-1" ref="btnBold" style="border-radius : 10px">
                                <b>굵게</b>
                            </button>
                        </div>
                    </div>
    
                </div>
                <div class="row justify-content-center" ref="bigScript" style="display:none">
                    <p class="col-11" ref="topic" style="margin-bottom: 0px; margin-top: 10px">{{ this.topic }}</p>
                    <h3 class="col-11" ref="question" style="margin-top : 10px; margin-left : 20px; margin-right : 20px">Q. {{ this.question }}</h3>
                    <h5 class="col-11" style="margin-top : 10px; margin-left : 10px; margin-right : 10px">[Script]</h5>
                    <div class="col-11" style="margin-top : 10px; margin-left : 10px; margin-right : 10px">
                        <div ref="script" style="display : inline; outline : none;"></div>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <button class="col-3 mx-2 button" @click.prevent="muteControl" ref="sound">
                    <img src="https://img.icons8.com/fluency-systems-regular/48/000000/microphone--v1.png"/>
                    음소거
                    </button>
                    <button class="col-3 mx-2 button" @click.prevent="screenControl" ref="screen">비디오 중지</button>
                </div>
            </div>

            <div ref="chat-page" class="col-4">
                <div class="chat-container" style="height : 92%; background-color : white; overflow: auto;">
                    <div ref="messageArea">
                    </div>
                </div>
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
<ConsultCloseModal @change="change"></ConsultCloseModal>
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
        width : 420,
        height : 210,
        facingMode : "user"
    },

    audio : true,
};

var peerConnection = new RTCPeerConnection(configuration);
var dataChannel = peerConnection.createDataChannel("dataChannel", { reliable: true });
var stompClient = null;

export default {
    data(){
        return {
            trigger : 0
        }
    },
    props: {
        room : {
            type: String,
            default: ""
        },
        topic : {
            type: String,
            default: ""
        },
        script : {
            type: String,
            default: ""
        },
        question : {
            type: String,
            default: ""
        },
    },
    mounted() {
        const fontSizeSelector = this.$refs.fontSize;
        const selectFontColor = this.$refs.fontColor
        const selectFontBackground = this.$refs.fontBackground
        var messageArea = this.$refs.messageArea;
        var username = this.$store.state.auth.user.username;
        var room = this.$route.params.room;
        var videoInput = this.$refs.videoInput
        var videoOutput = this.$refs.videoOutput
        var script = this.$refs.script
        var pre = this.$refs.script.innerHTML
        let btnBold = this.$refs.btnBold
        let role = this.$store.state.auth.user.role
        let inboundStream = null;
        let scriptButton = this.$refs.scriptButton
        let editorMenu = this.$refs.editorMenu
        let bigScript = this.$refs.bigScript
        let inputOuter = this.$refs.inputOuter
        let outputOuter = this.$refs.outputOuter

        script.innerText = this.$route.params.script

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
                var messageElement = document.createElement('p');
                messageElement.classList.add('event-message');
                messageElement.innerText = message.sender + ' 님이 입장하셨습니다.';
            } else if (message.type === 'LEAVE') {
                var messageElement = document.createElement('p');
                messageElement.classList.add('event-message');
                messageElement.innerText = message.sender + ' 님이 퇴장하셨습니다.';
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
                if (username != message.sender){
                    script.innerHTML = message.content
                }
            }
            if (message.type === 'JOIN'|| message.type === 'LEAVE'){
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
                    videoOutput.setAttribute('style','border-radius: 10px; display: inline')
                } else if (event.data === '스크립트 시작'){
                    bigScript.setAttribute("style","background-color : white; height: 300px; margin-bottom : 10px")
                    inputOuter.classList.remove("col-8")
                    inputOuter.classList.add("col-6")
                    outputOuter.classList.remove("col-8")
                    outputOuter.classList.add("col-6")
                } else if (event.data === '스크립트 중지'){
                    bigScript.setAttribute("style", "display : none")
                    inputOuter.classList.remove("col-6")
                    inputOuter.classList.add("col-8")
                    outputOuter.classList.remove("col-6")
                    outputOuter.classList.add("col-8")
                } else {
                    videoOutput.setAttribute("style","display:none")
                } 
            }else {
                var data = JSON.parse(event.data)
                var messageElement = document.createElement('p');
                messageElement.classList.add('chat-message');
                messageElement.innerText = data.username + ': ' + data.message
                messageArea.appendChild(messageElement)            
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
                scriptButton.setAttribute("style", "display : inline")
                script.setAttribute("contenteditable", "true")
                editorMenu.removeAttribute("style")
                bigScript.setAttribute("style","background-color : white; height: 40vh; margin-bottom : 10px; overflow : auto")
                console.log(inputOuter)
                inputOuter.classList.remove("col-8")
                inputOuter.classList.add("col-6")
                outputOuter.classList.remove("col-8")
                outputOuter.classList.add("col-6")
            }
            if(username) {
                var socket = new SockJS('https://i7b202.p.ssafy.io/ws');
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
        
        var interval = setInterval(() => {
            if (this.trigger == 1){
                clearInterval(interval)
            }
            var post = script.innerHTML            
            if (pre != post){
                stompClient.send('/topic/public/' + room,
                        JSON.stringify({
                            content : post,
                            sender : username
                        }),
                        {}
                );
                pre = post
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
            if (this.$refs.scriptButton.innerText === 'Script ON'){
                //dataChannel.send("스크립트 시작")
                this.$refs.scriptButton.innerText = "Script OFF"
            }else {
                //dataChannel.send("스크립트 중지")
                this.$refs.scriptButton.innerText = "Script ON"
            }
        },
        screenControl(){
            if (this.$refs.screen.innerText === '비디오 중지'){
                this.$refs.inputOuter.setAttribute("style","display:none;")
                dataChannel.send("비디오 중지")
                this.$refs.screen.innerText = "비디오 시작"
            }else {
                this.$refs.inputOuter.setAttribute("style","display:inline")
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

                var messageElement = document.createElement('p');
                messageElement.classList.add('chat-message');
                messageElement.innerText = this.$store.state.auth.user.username + ': ' + messageContent
                this.$refs.messageArea.appendChild(messageElement) 
                this.$refs.messageArea.scrollTop = this.$refs.messageArea.scrollHeight;
                this.$refs.messageInput.value = '';
            }
        },
        change(value) {
            this.trigger = value
        }
    },
    components: { ConsultCloseModal }
};

</script>

<style>
    @import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css");

    *:focus {
        outline: none;
    }
    font {
        padding: 0px !important;
    }
    .button {
        margin-top: 1.2rem;
        background-color: #0742F2;
        border-radius: 12px;
        color: white;
        cursor: pointer;
        font-weight: bold;
        text-align: center;
        transition: 200ms;
        width: 100%;
        box-sizing: border-box;
        border: 0;
        user-select: none;
        -webkit-user-select: none;
        touch-action: manipulation;
        }

    .button:not(:disabled):hover,
    .button:not(:disabled):focus {
    outline: 0;
    background: #0742F2;
    box-shadow: 0 0 0 2px rgba(0,0,0,.2), 0 3px 8px 0 rgba(0,0,0,.15);
    }

    .button:disabled {
    filter: saturate(0.2) opacity(0.5);
    -webkit-filter: saturate(0.2) opacity(0.5);
    cursor: not-allowed;
    }
    
    select {
        border-radius: 10px;
    }

    .outer {
        border-radius: 10%;
        overflow: hidden;
        padding : 0px !important;
        margin : 0px !important;
    }

    video {
        width : 100%;
        height : 100%
    }
</style>
