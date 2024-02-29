<template>
  <div>
    <template>
      <div>
        <b-container fluid>
          <b-breadcrumb :items="items"></b-breadcrumb>
          <b-card title="Tu espacio a sido rentado" class="mb-2">
            <b-card-text>
              Podras ver la información de tu espacio en la sección de "Mis espacios" y esperar a que el cliente te contacte.
              <b-button href="/#/LandsPage" variant="primary">Volver</b-button>
            </b-card-text>

          </b-card>
        </b-container>
      </div>
    </template>
  </div>
</template>
  
<script>

export default {
  name: 'OfertarEspacio',

  data() {
    return {
      items: [
        {
          text: "Lands Page",
          to: { name: "LandsPage" },
        },
        {
          text: "Rentar un espacio",
          to: { name: "RentarEspacio" },
        },
        {
          text: "Información de mi espacio",
          to: { name: "InfoEspacios" },
        },
        {
          text: "Oferta tu espacio",
          active: true,
        },
      ],
    }
  }
}
</script>
  
                                                           