<template>
  <div>
    <template>
      <div>
        <b-container fluid>
          <b-breadcrumb :items="items"></b-breadcrumb>

          <b-row>
            <b-col>
              <b-form-group label="Nombre del espacio">
                <b-form-input v-model="nombre" placeholder="Nombre del espacio"></b-form-input>
              </b-form-group>
            </b-col>
          </b-row>

          <b-row>
            <b-col>
              <b-form-group label="Descripción">
                <b-form-textarea v-model="descripcion" placeholder="Descripción del espacio"></b-form-textarea>
              </b-form-group>
            </b-col>
          </b-row>

          <b-row>
            <b-col>
              <b-form-group label="Precio">
                <b-form-input v-model="precio" placeholder="Precio del espacio"></b-form-input>
              </b-form-group>
            </b-col>
          </b-row>

          <b-row>
            <b-col>
              <b-form-group label="Ubicación">
                <b-form-input v-model="ubicacion" placeholder="Ubicación del espacio"></b-form-input>
              </b-form-group>
            </b-col>
          </b-row>

          <b-row>
            <b-col>
              <b-form-group label="Habitaciones">
                <b-form-input v-model="habitaciones" placeholder="Habitaciones del espacio"></b-form-input>
              </b-form-group>
            </b-col>
          </b-row>

          <b-row>
            <b-col>
              <b-form-group label="Baños">
                <b-form-input v-model="banos" placeholder="Baños del espacio"></b-form-input>
              </b-form-group>
            </b-col>
          </b-row>

          <b-row>
            <b-col>
              <b-form-group label="Imagen">
                <b-form-file v-model="imagen" placeholder="Imagen del espacio"></b-form-file>
              </b-form-group>
            </b-col>
          </b-row>

          <b-row class="mt-3">
            <b-col>
              <b-button variant="primary" href="/#/OfertarEspacio">Ofertar espacio</b-button>
            </b-col>
          </b-row>
        </b-container>
      </div>
    </template>
  </div>
</template>
  
<script>

export default {
  name: 'InfoEspacios',
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
          active: true,
        },
      ],
    }
  }
}
</script>
  
                                                           