<template>
  <div class="history-modal">
    <b-modal :active="isShown" :on-cancel="closeModal">
      <h1 class="title is-white">History for the record with id {{ id }}</h1>
      <b-table
        :data="data"
        :sort-icon="'arrow-up'"
        :sort-icon-size="'is-small'"
        :default-sort-direction="'asc'"
        :default-sort="'status'"
        detail-key="id"
        striped
        hoverable
      >
        <b-table-column field="id" label="ID" width="40" numeric v-slot="props">
          {{ props.row.id }}
        </b-table-column>

        <b-table-column
          field="name"
          label="Name"
          centered
          v-slot="props"
        >
          {{ props.row.name }}
        </b-table-column>

        <b-table-column
          field="status"
          label="Status"
          centered
          width="200"
          v-slot="props"
        >
          {{ props.row.status }}
        </b-table-column>

        <b-table-column field="date" label="Date" centered v-slot="props">
          {{ props.row.date }}
        </b-table-column>
      </b-table>
    </b-modal>
  </div>
</template>

<script>
export default {
  name: "HistoryModal",
  props: ["id", "isShown", "data"],
  data() {
    return {
    };
  },
  methods: {
    closeModal() {
      return this.$emit("close-modal");
    },
  }
};
</script>

<style scoped>

.is-white {
  color: white;
}
</style>
