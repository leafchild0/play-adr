<template>
  <div class="home">
    <RecordsTable :data="data" :loading="false"></RecordsTable>
  </div>
</template>

<script>
import RecordsTable from "@/components/RecordsTable";
export default {
  name: "Home",
  components: {RecordsTable},
  data() {
    return {
      data: [
        { 'id': 1, 'name': 'Learn Play Framework', 'status': 'SUGGESTED', 'date': '2016-10-15 13:43:27'},
        { 'id': 2, 'name': 'Create this app', 'status': 'APPROVED', 'date': '2016-12-15 06:00:53'},
        { 'id': 3, 'name': 'Use Gradle instead of sbt', 'status': 'SUGGESTED', 'date': '2016-04-26 06:26:28'},
        { 'id': 4, 'name': 'Implement Mongo schema', 'status': 'APPROVED', 'date': '2016-04-10 10:28:46'},
        { 'id': 5, 'name': 'Add Spring to the project', 'status': 'SUSPENDED', 'date': '2016-12-06 14:38:38'}
      ],
    }
  }
};
</script>
