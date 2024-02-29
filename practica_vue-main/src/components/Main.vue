<template>
    <div>
      Main
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
    name: "Main",
    data() {
      return {
        items: [
          {
            text: "Tercero",
            href: "#",
            to: { name: "tercero" },
          },
          {
            text: "Inicio",
            to: { name: "inicio" },
          },
          {
            text: "Main",
            active: true,
          },
        ],
      };
    },
  });
  </script>