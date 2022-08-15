<template>
<div :id="item" :class="isSelect()" @click="selectTopic" :style="style_btn">
<p>{{item}}</p>
</div>
</template>

<script>
import { mapActions,mapGetters } from 'vuex';
export default {
    name: "InputTopic",

    data() {
        return{
            style_btn:''
        }
    },

    props : {
      item: String,
      idx: Number
    },
    methods: {
      ...mapActions(['fetchSelectTopicIdx']),
      selectTopic() {
        this.fetchSelectTopicIdx(this.idx)
        console.log(this.idx,this.selectTopicIdx)
        this.$emit("topic", this.item);
        },
      isSelect(){
        return  this.idx === this.selectTopicIdx ? 'select' : ''
      },
    },
    computed:{
      ...mapGetters(['selectTopicIdx'])

    }
}
</script>

<style scoped>
.select{
  background-color:#004ACC;
  color:white;
  font-weight: bold;
}
div{
  font-size: 16px;
  margin: 0.5rem;
  border-radius: 50px;
  height: 3rem;
  vertical-align: middle; 
  border: 1px solid rgb(216, 216, 216);
}
p{
  margin: 0;
  padding: 0.5rem;
  letter-spacing: 2px;
}
</style>