<template>
  <div class="edit-record">
    <b-field horizontal label="Name">
      <b-input v-model="record.name"></b-input>
    </b-field>

    <b-field horizontal label="Date">
      <b-input v-model="record.date" disabled></b-input>
    </b-field>

    <b-field horizontal label="Status">
      <b-tag :type="statusType">{{ record.status }}</b-tag>
    </b-field>

    <b-field horizontal label="Proposal">
      <wysiwyg v-model="record.body" class="wysiwyg"></wysiwyg>
    </b-field>
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
    isNew() {
      return this.$route.params.id === 'new'
    },
    statusType() {
      let color = '';
      if (!this.isNew) {
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
  mounted() {
    if (!this.isNew) {
      // Load record
      this.record = {
        id: 1,
        name: "Learn Play Framework",
        status: "SUGGESTED",
        date: "2016-10-15 13:43:27",
        body: `<h2 id="-context-"><strong>Context</strong></h2>
<p>What is the context of this decision? It is important to capture the full context of the decision so that the reader knows the reasons behind it.</p>
<h2 id="-decision-"><strong>Decision</strong></h2>
<p>The decision that was made. For instance, use <a href="https://www.elastic.co/">Elasticsearch</a> for an enterprise-wide search API.</p>
<h2 id="-consequences-"><strong>Consequences</strong></h2>
<p> <em>**</em>In this section, you can add what would happen if this decision is made. It is important to list all consequences, both positive and negative.</p>
`
      }
    }
  }
}
</script>

<style lang="scss" scoped>

.edit-record {
  margin: 0 20px;
  display: flex;
  justify-items: center;
  flex-direction: column;
  max-width: 80%;
}

</style>
