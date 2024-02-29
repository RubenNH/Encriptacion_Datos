<template>
  <div>
    <div>
      <b-container fluid>
        <b-breadcrumb :items="items"></b-breadcrumb>
      </b-container>
    </div>


    <b-container fluid>
      <b-row>

        <b-card title="Renta TU espacio" img-src="https://picsum.photos/600/300/?image=25" img-alt="Image" img-top tag="article"
          style="max-width: 20rem;" class="mb-2">
          <b-card-text>
            Renta tu espacio a un precio justo a personas que lo necesitan.
          </b-card-text>

          <b-button href="/#/RentarTuEspacio" variant="primary">Ofertar</b-button>
        </b-card>

        <b-card title="Rentar un espacio" img-src="https://picsum.photos/seed/picsum/600/300" img-alt="Image" img-top tag="article"
          style="max-width: 20rem; margin-left: 2%;" class="mb-2">
          <b-card-text>
            Encuentra el espacio que necesitas para tu evento.
          </b-card-text>

          <b-button href="/#/RentarEspacio" variant="primary">Descubrir</b-button>
        </b-card>
      </b-row>


    </b-container>

  </div>
</template>
  
<script>
import Vue from "vue";

export default Vue.extend({
  name: "InicioLandspage",
  data() {
    return {
      items: [
        {
          text: "Lands Page",
          active: true,
        },
      ],
    };
  },
});
</script>