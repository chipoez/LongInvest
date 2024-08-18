

<template>
  <div>
    <input type="file" @change="handleFileUpload" />
    <input type="text" v-model="code" placeholder="Enter business code" />
    <button @click="uploadFile">Upload</button>
  </div>

</template>

<script setup lang="ts">
import axios from 'axios';
export default {
  data() {
    return {
      file: null,
      code: ''
    };
  },
  methods: {
    handleFileUpload(event) {
      this.file = event.target.files[0];
    },
    async uploadFile() {
      if (!this.file || !this.code) {
        alert('Please select a file and enter a business code.');
        return;
      }
      const formData = new FormData();
      formData.append('file', this.file);
      formData.append('code', this.code);
      try {
        const response = await axios.post('/api/csv/upload', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
        console.log(response.data);
      } catch (error) {
        console.error(error);
      }
    }
  }
};
</script>
<style scoped lang="less">

</style>
