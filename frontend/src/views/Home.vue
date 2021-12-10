<template>
  <div class="home">
    <b-button type="is-light" rounded icon-left="bookmark-plus" @click="createNewRecord">Add new</b-button>
    <RecordsTable :data="data" :loading="false"></RecordsTable>
  </div>
</template>

<script>
import RecordsTable from "@/components/RecordsTable";

export default {
  name: "Home",
  components: { RecordsTable },
  data() {
    return {
      data: [
        {
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
`,
        },
        {
          id: 2,
          name: "Create this app",
          status: "APPROVED",
          date: "2016-12-15 06:00:53",
        },
        {
          id: 3,
          name: "Use Gradle instead of sbt",
          status: "SUGGESTED",
          date: "2016-04-26 06:26:28",
        },
        {
          id: 4,
          name: "Implement Mongo schema",
          status: "APPROVED",
          date: "2016-04-10 10:28:46",
        },
        {
          id: 5,
          name: "Add Spring to the project",
          status: "SUSPENDED",
          date: "2016-12-06 14:38:38",
        },
      ],
    };
  },
  methods: {
    createNewRecord() {
      this.$router.push({name: 'edit', params: { id: 'new' } });
    }
  }
};
</script>
