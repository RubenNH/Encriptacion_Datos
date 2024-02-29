<template>
    <b-modal id="modal-lg" size="" title="Solicitar sitio" hide-footer>
        <!-- Fomrulario donde establecera las fechas que quiere, cantidad de persona y se calcula el costo final siendo este el precio x persona x dia -->
        <b-container fluid class="mt-4">
            <b-row>
                <b-col cols="12" md="12" style="margin-top: -7%;">
                    <h5>
                        Datos de la solicitud
                    </h5>
                </b-col>
            </b-row>
            <b-row>
                <b-col cols="6">
                    <b-form-group label="Fecha de llegada">
                        <b-form-datepicker id="example-datepicker" class="mb-2" :date-format-options="{ dateFormat }"
                            v-model="solicitud.fechaLlegada" :min="min"></b-form-datepicker>
                    </b-form-group>
                </b-col>
                <b-col cols="6">
                    <b-form-group label="Fecha de salida">
                        <b-form-datepicker id="example-datepicker2" class="mb-2" :date-format-options="{ dateFormat }"
                            v-model="solicitud.fechaSalida" :min="solicitud.fechaLlegada"></b-form-datepicker>
                    </b-form-group>
                </b-col>
            </b-row>
            <b-row>
                <b-col cols="12" md="12">
                    <b-form-group label="Cantidad de personas">
                        <b-form-spinbutton id="demo-sb" min="1" :max="datosLugar.cantidadMaximaPersonas" size="lg"
                            v-model="solicitud.cantidadPersonas"></b-form-spinbutton>
                    </b-form-group>
                </b-col>
            </b-row>
            <b-row>
                <b-col cols="12" md="12" class="mt-4">
                    <b-button @click="enviarSolicitud" class="btn-pinky" v-b-modal.modal-lg
                        style="width: 100%; height: 50px; font-size: 1.5rem; font-weight: bold;">Solicitar</b-button>
                    <p class="text-center">Aún no se te cobrará nada</p>
                </b-col>
            </b-row>
            <!-- Informacion de compra -->
            <b-row class="mt-1">
                <b-col cols="12" md="12">
                    <h5>
                        Información de compra
                    </h5>
                </b-col>
            </b-row>
            <b-row align-h="between">
                <b-col cols="8">
                    <p> ${{ parseFloat(datosLugar.precio).toFixed(2) }} X {{ solicitud.cantidadPersonas }} personas X {{ diasEstancia }} días  </p>
                    <p> Tarifa por servicio </p>
                    <h5> Total antes de impuestos</h5>
                </b-col>
                <b-col cols="4">
                    <P class="text-end"> ${{ totalBruto }} MXN </P>
                    <P class="text-end"> ${{ comision }} MXN </P>
                    <h5 class="text-end"> ${{ totalFinal }} MXN </h5>
                </b-col>
            </b-row>
        </b-container>
    </b-modal>
</template>

<script>
import moment from 'moment';
export default {
    name: 'DoRequest',
    props: {
        datosLugar: Object,
    },
    data() {
        //set today date on format 'DD/MM/YYYY'
        let today = new Date().toISOString().substr(0, 10);
        let minDate = today;
        return {
            min: minDate,
            dateFormat: 'DD/MM/YYYY',
            solicitud: {
                fechaLlegada: new Date(),
                fechaSalida: new Date(),
                cantidadPersonas: 1
            },
            diasEstancia: 0,
            totalBruto: parseFloat(this.datosLugar.precio * 1 * 1).toFixed(2),
            comision: parseFloat(this.datosLugar.precio * 1 * 1 * 0.01).toFixed(2),
            totalFinal: (parseFloat(this.datosLugar.precio * 1 * 1 * 0.01) + parseFloat(this.datosLugar.precio * 1 * 1)).toFixed(2),
        }
    },
    methods: {
        enviarSolicitud() {
            console.log(this.solicitud);

        },
        getDiasEstancia(fechaLlegada, fechaSalida) {
            //fechaSalida = fechaSalida + 1 day
            if (fechaSalida < fechaLlegada) {
                this.diasEstancia = 0;
                this.solicitud.fechaSalida = this.solicitud.fechaLlegada;
            }
            fechaSalida = new Date(moment(fechaSalida));
            fechaLlegada = new Date(moment(fechaLlegada));
            fechaLlegada.setHours(0, 0, 0, 0);
            fechaSalida.setHours(0, 0, 0, 0);
            this.diasEstancia = moment(fechaSalida).diff(moment(fechaLlegada), 'days') + 1;

            this.totalBruto = parseFloat(this.datosLugar.precio * this.solicitud.cantidadPersonas * this.diasEstancia).toFixed(2);
            this.comision = parseFloat(this.totalBruto * 0.01).toFixed(2);
            this.totalFinal = (parseFloat(this.totalBruto) + parseFloat(this.comision)).toFixed(2);
        },
    },
    watch: {
        solicitud: {
            handler() {
                this.getDiasEstancia(this.solicitud.fechaLlegada, this.solicitud.fechaSalida);
            },
            deep: true
        },
        diasEstancia() {
            this.totalBruto = parseFloat(this.datosLugar.precio * this.solicitud.cantidadPersonas * this.diasEstancia).toFixed(2);
        },

    }
}
</script>

<style></style>