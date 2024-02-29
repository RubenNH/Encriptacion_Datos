<template>
  <b-modal id="modal-1" title="Solicitudes de Reservación" size="lg" hide-footer>

    <b-container fluid class="mt-2">
      <b-row v-for="solicitud in solicitudes" :key="solicitud.idSoliciud" class="mb-3">
        <b-card style="max-width: 100%;">
          <div class="text-center mb-3">
            <h5 style="display: inline;">Tienes una nueva solicitud para: </h5>
            <h5 style="display: inline;" class="turquoise"> {{ solicitud.lugar.nombre }}</h5>
          </div>

          <b-row>
            <b-col col md="5">
              <b-list-group-item class="d-flex align-items-center mb-2">
                <b-avatar :src="solicitud.cliente.foto" size="4rem" variant="light"></b-avatar>
                <div style="margin-left: 4%;">
                  <span> {{ solicitud.cliente.nombre }}</span>
                  <div id="caalificacion">
                    <div id="stars-users" style="display: inline; ">
                      <b-icon-star-fill v-for="i in Math.floor(solicitud.cliente.stars)" :key="'fill-' + i"
                        class="my-stars"></b-icon-star-fill>
                      <b-icon-star-half v-if="solicitud.cliente.stars % 1 !== 0" :key="'half'"
                        class="my-stars"></b-icon-star-half>
                      <b-icon-star v-for="j in 5 - Math.ceil(solicitud.cliente.stars)" :key="'empty-' + j"
                        class="my-stars"></b-icon-star>
                    </div>
                    <p style=" display: inline;"> {{ solicitud.cliente.stars }} estrellas</p>
                  </div>
                  <span> {{ solicitud.cliente.reservacionesPasadas }} reservaciones</span>
                </div>
              </b-list-group-item>
            </b-col>
            <b-col col md="7">
              <b-row align-h="between">
                <b-col col md="6">
                  <p> {{ solicitud.cantidadPersonas }} personas X {{ solicitud.diasEstancia }} días </p>
                </b-col>
                <b-col col md="6">
                  <P class="text-end"> ${{ solicitud.total }} MXN </P>
                </b-col>
                <p> Del {{ solicitud.fechaInicio }} al {{ solicitud.fechaFin }} </p>
              </b-row>
            </b-col>
          </b-row>
          <b-row class="mt-3">
            <b-col col md="8">
              <b-button href="#" class="btn-pinky mb-2" style="max-width: 100%; min-width: 100%"><b-icon
                  icon="gem"></b-icon> Aceptar</b-button>
            </b-col>
            <b-col col md="4">
              <b-button href="#" class="btn-bluegreen mb-2" style="max-width: 100%; min-width: 100%"><b-icon
                  icon="trash"></b-icon> Declinar
              </b-button>
            </b-col>
          </b-row>
        </b-card>
      </b-row>
    </b-container>
  </b-modal>
</template>

<script>
export default {
  name: 'SolicitudesReservacion',
  props: {
    solicitudes: Array
  }
}
</script>

<style></style>
