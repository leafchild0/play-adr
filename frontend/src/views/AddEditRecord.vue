<template>
  <div class="edit-record">
    <b-field horizontal label="Name">
      <b-input v-model="record.name" maxlength="100" required></b-input>
    </b-field>

    <b-field horizontal label="Date" v-if="isEdit">
      <b-input v-model="record.date" disabled></b-input>
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
export default {
  name: "AddEditRecord",
  data() {
    return {
      record: {}
    };
  },
  computed: {
    id() {
      return this.$route.params.id
    },
    isEdit() {
      return this.$route.params.id !== 'new'
    },
    statusType() {
      let color = '';
      if (this.isEdit) {
        switch (this.record.status) {
          case 'APPROVED':
            color = 'is-success';
            break
          case 'SUGGESTED':
          default:
            color = 'is-info';
            break
          case 'SUSPENDED':
            color = 'is-danger';
            break
        }
      }
      return color;
    }
  },
  methods: {
    goBack() {
      this.$router.push('/');
    },
    saveRecord() {
      // Validate data
      if(this.validateData()) {

        // Save record

        // Go back
        this.goBack();

      }
    },
    validateData() {
      if (!this.record.context || !this.record.decision || !this.record.consequences) {
        this.$buefy.snackbar.open({message: `Some of the required fields are empty`, type: 'is-danger'});
        return false;
      }
      else return true;
    }
  },
  mounted() {
    if (this.isEdit) {
      // Fetch record by id
      this.record = {
        id: 1,
        name: "Learn Play Framework",
        status: "SUGGESTED",
        date: "2016-10-15 13:43:27",
        context: `<p>What is the context of this decision? It is important to capture the full context of the decision so that the reader knows the reasons behind it.</p>`,
        decision: `<p>The decision that was made. For instance, use <a href="https://www.elastic.co/">Elasticsearch</a> for an enterprise-wide search API.</p>`,
        consequences: `<p>In this section, you can add what would happen if this decision is made. It is important to list all consequences, both positive and negative.</p>`
      }
    }
  }
}
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
