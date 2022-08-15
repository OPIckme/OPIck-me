<template>
<nav class="navbar sticky-top" style="background-color:#052A99; height: 80px;">
  <div class="container">
    <router-link class="position-absolute top-50 start-50 translate-middle  " to="/mainpage">
      <img src="../assets/logo.png" alt="" style="width:120px">
    </router-link>
    <button class="Logout" style="color:white; background-color:#F2CB05; text-decoration: none;" data-bs-toggle="modal" data-bs-target="#Consultclose" >상담종료</button>
  </div>
</nav>
<div class="row" style="width : 100vw; margin:0px">
    <div class="col-8" style="padding: 0px">
        <div ref="videoContainer" style="width: 66.7vw; margin : 0px">
            <div class="outer" ref="outputOuter" style="width: 508px; height: 263.75px; margin : auto">
                <video id="videoOutput" autoplay style="display : inline;"
                ref="videoOutput"></video>
            </div>
            <div class="outer" ref="inputOuter" style="width: 508px; height: 263.75px; margin : auto">
                <video id="videoInput" autoplay style="display : inline;" muted="true" 
                ref="videoInput"></video>
            </div>
        </div>
        <div ref="sideBar" class="row justify-content-between my-2 mx-3" style="display : none">
            <button class="col-3 button" @click.prevent="scriptControl" ref="scriptButton" style="display:inline">Script ON</button>
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
        <div ref="bigScript" style="display:none">
            <p ref="topic" style="margin-bottom: 0px; margin-top: 10px; margin-left : 50px">{{ this.topic }}</p>
            <h3 ref="question" style="margin-top : 10px; margin-left : 50px; margin-right : 50px; font-weight: bold;">Q. {{ this.question }}</h3>
            <h5 style="margin-top : 20px; margin-bottom : 10px; margin-left : 50px; margin-right : 10px">[Script]</h5>
            <div style="margin-top: 20px; margin-left : 50px; margin-right: 50px;">
                <div ref="script" style="display : inline; outline : none;"></div>
            </div>
        </div>
        <div class="row justify-content-center mb-2">
            <button class="col-3 mx-2 button" @click.prevent="muteControl" ref="sound">

            음소거
            </button>
            <button class="col-3 mx-2 button" @click.prevent="screenControl" ref="screen">비디오 중지</button>
        </div>
    </div>

    <div ref="chat-page" class="col-4" style="padding-right : 0px">
        <div ref="messageArea" style="height : 82vh; background-color : #E3F2FD;overflow-y:auto">
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
<ConsultCloseModal :modify="modify" :scriptId="scriptId" @change="change" @closeControl="closeControl"></ConsultCloseModal>
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
        width : 508,
        height : 263.75,
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
            trigger : 0,
            modify : ''
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
        scriptId:{
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
        let bigScript = this.$refs.bigScript
        let inputOuter = this.$refs.inputOuter
        let outputOuter = this.$refs.outputOuter
        let sideBar = this.$refs.sideBar
        let videoContainer = this.$refs.videoContainer
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
                messageElement.setAttribute("style","margin:10px auto; border: solid; border-width: 0px; border-radius: 15px; background-color: lightgray; text-align: center; width: 70%; height: 35px; line-height : 35px")
                messageElement.innerText = message.sender + ' 님이 입장하셨습니다.';
            } else if (message.type === 'LEAVE') {
                var messageElement = document.createElement('p');
                messageElement.setAttribute("style","margin:10px auto; text-align: center; width: 60%")
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
            } 
            if (message.type === 'JOIN'|| message.type === 'LEAVE'){
                messageArea.appendChild(messageElement);
                messageArea.scrollTop = messageArea.scrollHeight;
            }
        }

        peerConnection.ondatachannel = (event) => {
            dataChannel = event.channel;
        };

        dataChannel.onmessage = (event) => {
            if (event.data === '비디오 시작' || event.data === '비디오 중지'
            || event.data === '음소거' || event.data === '음소거 해제'
            || event.data === '스크립트 시작' || event.data === '스크립트 중지' || event.data === '상담종료'){
                if (event.data === '음소거'){
                    videoOutput.srcObject.getAudioTracks()[0].enabled = false
                } else if (event.data === '음소거 해제'){
                    videoOutput.srcObject.getAudioTracks()[0].enabled = true
                } else if (event.data === '비디오 시작'){
                    videoOutput.setAttribute('style','display: inline')
                } else if (event.data === '스크립트 시작'){
                    bigScript.setAttribute("style","background-color : #E3F2FD; height: 48.5vh; margin-bottom : 10px; margin-left : 20px; margin-right : 10px; overflow: auto")
                    constraints.video.width = 420
                    constraints.video.height = 220
                    videoContainer.classList.add("row")
                    videoContainer.classList.add("justify-content-center")
                    inputOuter.setAttribute("style",
                        "border-radius: 30px;overflow: hidden;padding : 0px !important; margin-left: 10px; margin-right: 20px; margin-top : 15px; margin-bottom : 15px; width: 420px; height: 220px;")
                    outputOuter.setAttribute("style",
                        "border-radius: 30px;overflow: hidden;padding : 0px !important; margin-left: 10px; margin-right: 20px; margin-top : 15px; margin-bottom : 15px; width: 420px; height: 220px;")
                } else if (event.data === '스크립트 중지'){
                    bigScript.setAttribute("style", "display : none")
                    constraints.video.width = 508
                    constraints.video.height = 263.75
                    videoContainer.classList.remove("row")
                    videoContainer.classList.remove("justify-content-center")
                    inputOuter.setAttribute("style",
                        "border-radius: 30px;overflow: hidden;padding : 0px !important; margin : auto;margin-top : 15px; margin-bottom : 15px; width: 508px; height: 263.75px;")
                    outputOuter.setAttribute("style",
                        "border-radius: 30px;overflow: hidden;padding : 0px !important; margin : auto;margin-top : 15px; margin-bottom : 15px; width: 508px; height: 263.75px;")
                } else if (event.data === '상담종료'){
                    peerConnection.close()
                    this.$router.push("/feedback")
                } else {
                    videoOutput.setAttribute("style","display:none")
                } 
            }else {
                var data = JSON.parse(event.data)
                console.log(data)
                if (data.content != null && username != data.sender){
                    script.innerHTML = data.content
                    bigScript.scrollTop = data.scroll
                }else{
                    var messageElement = document.createElement('div');
                    messageElement.classList.add("chat-message")
                    messageElement.classList.add("other")
                    var messageText = document.createElement('div')
                    messageText.innerText = data.message
        
                    messageElement.appendChild(messageText)
                    messageArea.appendChild(messageElement)  
                    messageArea.scrollTop = messageArea.scrollHeight;
                }          
            }
        };

        peerConnection.addEventListener('connectionstatechange', event => {
            if (peerConnection.connectionState === 'connected') {
                console.log("Peers connected!")
                stompClient.disconnect() 
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
                sideBar.removeAttribute("style")
                script.setAttribute("contenteditable", "true")
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
            this.modify = post          
            if (dataChannel.readyState === 'open' && pre != post && this.$store.state.auth.user.role === 'consultant'){    
                dataChannel.send(JSON.stringify({
                    "sender" : this.$store.state.auth.user.username,
                    "content" : post,
                    "scroll" : bigScript.scrollHeight
                }))
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
                dataChannel.send("스크립트 시작")
                this.$refs.script.setAttribute("contenteditable", "true")
                this.$refs.editorMenu.removeAttribute("style")
                this.$refs.bigScript.setAttribute("style","background-color : #E3F2FD; height: 42vh; margin-bottom : 10px; margin-left : 20px; margin-right : 10px; overflow : auto")
                constraints.video.width = 420
                constraints.video.height = 220
                this.$refs.videoContainer.classList.add("row")
                this.$refs.videoContainer.classList.add("justify-content-center")
                this.$refs.inputOuter.setAttribute("style",
                    "border-radius: 30px;overflow: hidden;padding : 0px !important; margin-left: 10px; margin-right: 20px; margin-top : 15px; margin-bottom : 15px; width: 420px; height: 220px;")
                this.$refs.outputOuter.setAttribute("style",
                    "border-radius: 30px;overflow: hidden;padding : 0px !important; margin-left: 10px; margin-right: 20px; margin-top : 15px; margin-bottom : 15px; width: 420px; height: 220px;")
                this.$refs.scriptButton.innerText = "Script OFF"
            }else {
                dataChannel.send("스크립트 중지")
                this.$refs.editorMenu.setAttribute("style", "display: none")
                this.$refs.bigScript.setAttribute("style","display : none")
                constraints.video.width = 508
                constraints.video.height = 263.75
                this.$refs.videoContainer.classList.remove("row")
                this.$refs.videoContainer.classList.remove("justify-content-center")
                this.$refs.inputOuter.setAttribute("style",
                    "border-radius: 30px;overflow: hidden;padding : 0px !important; margin : auto;margin-top : 15px; margin-bottom : 15px; width: 508px; height: 263.75px;")
                this.$refs.outputOuter.setAttribute("style",
                    "border-radius: 30px;overflow: hidden;padding : 0px !important; margin : auto;margin-top : 15px; margin-bottom : 15px; width: 508px; height: 263.75px;")
                this.$refs.scriptButton.innerText = "Script ON"
            }
        },
        screenControl(){
            if (this.$refs.screen.innerText === '비디오 중지'){
                this.$refs.videoInput.setAttribute("style","display:none;")
                dataChannel.send("비디오 중지")
                this.$refs.screen.innerText = "비디오 시작"
            }else {
                this.$refs.videoInput.setAttribute("style","display:inline; mute:true")
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

                var messageElement = document.createElement('div');
                messageElement.classList.add("chat-message")
                messageElement.classList.add("mine")
                var messageText = document.createElement('div')
                messageText.innerText = messageContent
    
                messageElement.appendChild(messageText)
                this.$refs.messageArea.appendChild(messageElement)  
                this.$refs.messageInput.value = '';
                this.$refs.messageArea.scrollTop = this.$refs.messageArea.scrollHeight;
            } 
        },
        change(value) {
            this.trigger = value
        },
        closeControl(value) {
            dataChannel.send(value)
            peerConnection.close()
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
        background-color: #0742F2;
        border-radius: 12px;
        color: white;
        cursor: pointer;
        font-weight: bold;
        text-align: center;
        transition: 200ms;
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
        text-align: center;
    }

    .outer {
        border-radius: 30px;
        overflow: hidden;
        padding : 0px !important;
        margin-top : 15px !important;
        margin-bottom : 15px !important
    }

    video {
        width : 100%;
        height : 100%
    }

    .chat-message {
        position:relative;
        margin: 10px
    }

    .chat-message::after {
        content:"";
        display:block;
        clear:both;
    }

    .chat-message > div {
        background-color:white;
        float:left;
        padding:0.4rem;
        border-radius:0.6rem;
        clear:both;
        font-size:1rem;
        box-shadow: 1px 1px 0 rgba(0,0,0,0.3);
    }

    .chat-message.mine > div {
        background-color: #FDF01B;
        float:right;
        box-shadow: -1px 1px 0 rgba(0,0,0,0.3);
    }
    .navbar{
    z-index: 1;
    }
    .Logout {
    align-items: center;
    background-color: #F2CB05;
    border: 2px solid #111;
    border-radius: 50px;
    box-sizing: border-box;
    color: white;
    cursor: pointer;
    display: flex;
    font-family: Inter,sans-serif;
    font-size: 14px;
    font-weight: bold;
    height: 36px;
    justify-content: center;
    line-height: 24px;
    max-width: 100%;
    padding: 0 25px;
    position: relative;
    text-align: center;
    text-decoration: none;
    user-select: none;
    -webkit-user-select: none;
    touch-action: manipulation;
    position: relative;
    left: 85%;
    }

    .Logout:after {
    background-color: #111;
    border-radius: 50px;
    content: "";
    display: block;
    height: 36px;
    left: 0;
    width: 100%;
    position: absolute;
    top: -2px;
    transform: translate(6px, 6px);
    transition: transform .2s ease-out;
    z-index: -1;
    }

    .Logout:hover:after {
    transform: translate(0, 0);
    }

    .Logout:active {
    background-color: #F2CB05;
    outline: 0;
    }

    .LogLogoutin:hover {
    outline: 0;
    }

    @media (min-width: 768px) {
    .Logout {
        padding: 0 20px;
    }
    }
</style>
