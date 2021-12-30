<template>
  <div class="records-table">
    <b-table
      :data="data"
      :loading="loading"
      :sort-icon="'arrow-up'"
      :sort-icon-size="'is-small'"
      :default-sort-direction="'asc'"
      :default-sort="'status'"
      detail-key="id"
      detailed
      striped
      hoverable
    >
      <b-table-column field="id" label="ID" width="40" numeric v-slot="props">
        {{ props.row.id }}
      </b-table-column>

      <b-table-column
        field="name"
        label="Name"
        searchable
        centered
        v-slot="props"
      >
        {{ props.row.name }}
      </b-table-column>

      <b-table-column
        field="status"
        label="Status"
        searchable
        centered
        sortable
        width="200"
        v-slot="props"
      >
        {{ props.row.status }}
      </b-table-column>

      <b-table-column field="date" label="Date" centered v-slot="props">
        {{ props.row.date }}
      </b-table-column>

      <b-table-column custom-key="actions" label="Actions" v-slot="props">
        <b-button
          class="button is-small is-light"
          icon-left="pencil"
          type="is-primary is-light"
          @click="edit(props.row)"
        >
        </b-button>
        <b-button
          v-if="props.row.status === 'PROPOSED'"
          class="button is-small is-success"
          icon-left="check"
          type="is-success is-light"
          @click="approve(props.row)"
        >
        </b-button>
        <b-button
          v-if="props.row.status === 'APPROVED'"
          class="button is-small is-danger"
          icon-left="close-circle"
          type="is-danger is-light"
          @click="suspend(props.row)"
        >
        </b-button>
      </b-table-column>

      <template #detail="props">
        <article class="media">
          <div class="media-content">
            <div class="content">
              <div>
                <span class="content-name">{{ props.row.name }}</span>
                <div class="content-date">{{ props.row.date }}</div>
                <div v-html="props.row.decision"></div>
              </div>
            </div>
          </div>
        </article>
      </template>
    </b-table>
  </div>
</template>

<script>
import api from "../api/records";
// Table
export default {
  name: "RecordsTable",
  props: ["data", "loading"],
  data() {
    return {};
  },
  methods: {
    edit(row) {
      this.$router.push({ name: "edit", params: { id: row.id } });
    },
    approve(row) {
      row.status = "APPROVED";
      this.save(row);
    },
    suspend(row) {
      row.status = "SUSPENDED";
      this.save(row);
    },
    save(row) {
      // Save record
      api.updateRecord(row);
    },
  },
};
</script>

<style lang="scss" scoped>
.records-table {
  display: flex;
  justify-content: center;
  flex: 1 1 auto;
  min-width: 1px;
  max-width: 80%;
  margin: 0 auto;
  width: 100%;
}

.content-name {
  font-weight: 700;
  margin-right: 10px;
}
</style>
