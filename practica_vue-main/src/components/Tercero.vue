<template>
    <div>
      Tercero
      <div>
        <b-container>
          <b-breadcrumb :items="items"></b-breadcrumb>
        </b-container>
      </div>
    </div>
  </template>
  
  <script>
  import Vue from "vue";
  
  export default Vue.extend({
    name: "Tercero",
    data() {
      return {
        items: [
          {
            text: "Inicio",
            href: "#",
            to: { name: "inicio" },
          },
          {
            text: "Main",
            to: { name: "main" },
          },
          {
            text: "Tercero",
            active: true,
          },
        ],
      };
    },
  });
  </script>