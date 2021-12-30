<template>
  <div class="home">
    <b-button
      type="is-light"
      rounded
      icon-left="bookmark-plus"
      @click="createNewRecord"
      >Add new</b-button
    >
    <RecordsTable :data="data" :loading="false" @save="saveRecord"></RecordsTable>
  </div>
</template>

<script>
import RecordsTable from "@/components/RecordsTable";
import api from "../api/records";

export default {
  name: "Home",
  components: { RecordsTable },
  data() {
    return {
      data: [],
    };
  },
  methods: {
    createNewRecord() {
      this.$router.push({ name: "edit", params: { id: "new" } });
    },
    saveRecord(row) {
      api.saveRecord(row);
    },
  },
  mounted() {
    api
      .getRecords()
      .then((records) => {
        this.data = records;
      })
      .catch((e) => {
        console.error(e);
        this.$buefy.snackbar.open({
          message: `Oops, something bad happened`,
          type: "is-danger",
        });
      });
  },
};
</script>
