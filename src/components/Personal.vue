<template>
  <div id="personal_content">
    <Header></Header>
    <PersonalContentTitle></PersonalContentTitle>
    <PersonalContentCenter></PersonalContentCenter>
  </div>
</template>

<script setup>
import Header from "@/components/Home/Header.vue";
import PersonalContentTitle from "@/components/Personal/PersonalContentTitle.vue";
import PersonalContentCenter from "@/components/Personal/PersonalContentCenter.vue";
</script>

<style scoped>
#personal_content {
  background-color: #ffcc99;
  /* min-width: 1700px; */
  height: 100%;
}
</style>