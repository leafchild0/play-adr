<template>
  <div class="edit-record">
    <b-field horizontal label="Name">
      <b-input v-model="record.name" maxlength="100" required></b-input>
    </b-field>

    <b-field horizontal label="Date" v-if="isEdit">
      <b-input :value="record.date" disabled></b-input>
    </b-field>

    <b-field horizontal label="Status" v-if="isEdit">
      <b-tag :type="statusType">{{ record.status }}</b-tag>
    </b-field>

    <b-field horizontal label="Context">
      <wysiwyg v-model="record.context" class="wysiwyg"></wysiwyg>
    </b-field>

    <b-field horizontal label="Decision">
      <wysiwyg v-model="record.decision" class="wysiwyg"></wysiwyg>
    </b-field>

    <b-field horizontal label="Consequences">
      <wysiwyg v-model="record.consequences" class="wysiwyg"></wysiwyg>
    </b-field>
    <div class="controls">
      <b-button type="is-success" @click="saveRecord">Save</b-button>
      <b-button @click="goBack">Cancel</b-button>
    </div>
  </div>
</template>

<script>
import api from "../api/records";

export default {
  name: "AddEditRecord",
  data() {
    return {
      record: {},
    };
  },
  computed: {
    id() {
      return this.$route.params.id;
    },
    isEdit() {
      return this.$route.params.id !== "new";
    },
    statusType() {
      let color = "";
      if (this.isEdit) {
        switch (this.record.status) {
          case "APPROVED":
            color = "is-success";
            break;
          case "SUGGESTED":
          default:
            color = "is-info";
            break;
          case "SUSPENDED":
            color = "is-danger";
            break;
        }
      }
      return color;
    },
  },
  methods: {
    goBack() {
      this.$router.push("/");
    },
    saveRecord() {
      // Validate data
      if (this.validateData()) {
        // Save record
        if (this.isEdit) api.updateRecord(this.record.id, this.record);
        else {
          this.record.date = new Date().toDateString();
          this.record.status = "PROPOSED";
          api.saveRecord(this.record);
        }

        // Go back
        this.goBack();
      }
    },
    validateData() {
      if (
        !this.record.context ||
        !this.record.decision ||
        !this.record.consequences ||
        !this.record.name
      ) {
        this.$buefy.snackbar.open({
          message: `Some of the required fields are empty`,
          type: "is-danger",
        });
        return false;
      } else return true;
    },
  },
  mounted() {
    if (this.isEdit) {
      // Fetch record by id
      api
        .getRecord(this.id)
        .then((record) => {
          this.record = record;
        })
        .catch((e) => {
          console.error(e);
          this.$buefy.snackbar.open({
            message: `Oops, something bad happened`,
            type: "is-danger",
          });
        });
    }
  },
};
</script>

<style lang="scss" scoped>
.edit-record {
  display: flex;
  justify-items: center;
  flex-direction: column;
  max-width: 80%;
  margin: 0 auto;
}

.controls {
  button:nth-child(1) {
    margin-right: 10px;
  }
}
</style>
