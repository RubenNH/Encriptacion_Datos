<template>
    <div>
      <template>
        <div>
          <b-container fluid>
            <b-breadcrumb :items="items"></b-breadcrumb>

            <b-row>
                <b-col md="4">
                    <b-form-group label="Nombres" :state="validateNombres()">
                        <b-form-input v-model="nombres" placeholder="Nombres"></b-form-input>
                        <b-form-invalid-feedback :state="validateNombres()">Ingrese un nombre válido.</b-form-invalid-feedback>
                    </b-form-group>
                </b-col>
                <b-col md="3">
                    <b-form-group label="Apellido Paterno">
                        <b-form-input v-model="apellidoPaterno" placeholder="Apellido Paterno"></b-form-input>
                        <b-form-invalid-feedback :state="validatePaterno()">Ingrese un apellido válido.</b-form-invalid-feedback>
                    </b-form-group>
                </b-col>
                <b-col md="3">
                    <b-form-group label="Apellido Materno">
                        <b-form-input v-model="apellidoMaterno" placeholder="Apellido Materno"></b-form-input>
                    </b-form-group>
                </b-col>
            </b-row>
            <b-row class="mt-2 mb-2">
                <b-col md="2">
                    <b-form-group label="Codigo Postal">
                        <b-form-input v-model="codigoPostal" placeholder="Codigo Postal" type="number"></b-form-input>
                        <b-form-invalid-feedback :state="validateCodigoPostal()">Ingrese un codigo postal válido.</b-form-invalid-feedback>
                    </b-form-group>
                </b-col>
                <b-col md="3">
                    <b-form-group label="Calle">
                        <b-form-input v-model="calle" placeholder="Calle"></b-form-input>
                        <b-form-invalid-feedback :state="validateCalle()">Ingrese una calle válida.</b-form-invalid-feedback>
                    </b-form-group>
                </b-col>
                <b-col md="2">
                    <b-form-group label="Numero">
                        <b-form-input v-model="numero" placeholder="Numero" type="number"></b-form-input>
                        <b-form-invalid-feedback :state="validateNumero()">Ingrese un numero válido.</b-form-invalid-feedback>
                    </b-form-group>
                </b-col>
                <b-col md="3">
                    <b-form-group label="Ciudad">
                        <b-form-input v-model="ciudad" placeholder="Ciudad"></b-form-input>
                        <b-form-invalid-feedback :state="validateCiudad()">Ingrese una ciudad válida.</b-form-invalid-feedback>
                    </b-form-group>
                </b-col>
            </b-row>
            <b-row class="mt-2 mb-2">
                <b-col md="4">
                    <b-form-group label="Fecha de Nacimiento">
                        <b-form-input v-model="fechaNacimiento" placeholder="Fecha de Nacimiento" aria-invalid="false"  type="date"></b-form-input>
                        <b-form-invalid-feedback :state="validateFechaNacimiento()">Ingrese una fecha de nacimiento válida.</b-form-invalid-feedback>
                    </b-form-group>
                </b-col>
                <b-col md="3">
                    <b-form-group label="Correo Electronico">
                        <b-form-input v-model="correoElectronico" placeholder="Correo Electronico" type="email"></b-form-input>
                        <b-form-invalid-feedback :state="validateCorreoElectronico()">Ingrese un correo electrónico válido.</b-form-invalid-feedback>
                    </b-form-group>
                </b-col>
                <b-col md="3">
                    <b-form-group label="Telefono">
                        <b-form-input v-model="telefono" placeholder="Telefono" type="number"></b-form-input>
                        <b-form-invalid-feedback :state="validateTelefono()">Ingrese un telefono válido.</b-form-invalid-feedback>
                    </b-form-group>
                </b-col>
            </b-row>
            <b-row>
                <b-col>
                    <b-form-group label="Imagen">
                        <b-form-file v-model="imagen" placeholder="Imagen del usuario"></b-form-file>
                        <b-form-invalid-feedback :state="validateImagen()">Ingrese una imagen válida.</b-form-invalid-feedback>
                    </b-form-group>
                </b-col>
            </b-row>
            <b-row class="mt-3">
                <b-col>
                    <b-button variant="primary" @click="submitForm">Guardar</b-button>
                </b-col>
            </b-row>
          </b-container>
        </div>
      </template>
    </div>
  </template>
    
  <script>
  
  export default {
        name: 'formularioUsuario',
        data() {
          return {
            items: [
              {
                text: "Formulario Usuario",
                active: true,
              },
            ],
            nombres: "",
            apellidoPaterno: "",
            apellidoMaterno: "",
            codigoPostal: "",
            calle: "",
            numero: "",
            ciudad: "",
            fechaNacimiento: "",
            correoElectronico: "",
            telefono: "",
            imagen: null,
            openValidations: false,
          };
        },
        methods: {
            validateNombres() {
                if(this.openValidations){
                    if(this.nombres.length == 0 || !this.nombres.replace(/\s/g, '').length || !this.nombres.match(/^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$/)){
                        return false;
                    }
                }
                return true;
                
            },
            validatePaterno() {
                if(this.openValidations){
                    if(this.apellidoPaterno.length == 0 || !this.apellidoPaterno.replace(/\s/g, '').length || !this.apellidoPaterno.match(/^[a-zA-ZñÑáéíóúÁÉÍÓÚ\s]+$/)){
                        return false;
                    }
                }
                return true;
                
            },
            validateCodigoPostal() {
                if(this.openValidations){
                    if(this.codigoPostal.length == 0 || !this.codigoPostal.replace(/\s/g, '').length || !this.codigoPostal.match(/^[0-9]+$/) || this.codigoPostal.length != 5){
                        return false;
                    }
                }
                return true;
                
            },
            validateCalle() {
                if(this.openValidations){
                    if(this.calle.length == 0 || !this.calle.replace(/\s/g, '').length){
                        return false;
                    }
                }
                return true;
                
            },
            validateNumero() {
                if(this.openValidations){
                    if(this.numero.length == 0 || !this.numero.replace(/\s/g, '').length || !this.numero.match(/^[0-9]+$/)){
                        return false;
                    }
                }
                return true;
            },
            validateCiudad() {
                if(this.openValidations){
                    if(this.ciudad.length == 0 || !this.ciudad.replace(/\s/g, '').length){
                        return false;
                    }
                }
                return true;
            },
            validateFechaNacimiento() {
                if(this.openValidations){
                    //Fecha de nacimiento - No es presente ni futura, poner un límte de 18 años, tampoco nula
                    var fechaActual = new Date();
                    var fechaNacimiento = new Date(this.fechaNacimiento);
                    var edad = fechaActual.getFullYear() - fechaNacimiento.getFullYear();
                    var mes = fechaActual.getMonth() - fechaNacimiento.getMonth();
                    if (mes < 0 || (mes === 0 && fechaActual.getDate() < fechaNacimiento.getDate())) {
                        edad--;
                    }
                    if(this.fechaNacimiento.length == 0 || !this.fechaNacimiento.replace(/\s/g, '').length || edad < 18){
                        return false;
                    }

                }
                return true;
                
            },
            validateCorreoElectronico() {
                if(this.openValidations){
                    if(this.correoElectronico.length == 0 || !this.correoElectronico.replace(/\s/g, '').length || !this.correoElectronico.match(/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/)){
                        return false;
                    }
                }
                return true;
                
            },
            validateTelefono() {
                if(this.openValidations){
                    if(this.telefono.length == 0 || !this.telefono.replace(/\s/g, '').length || !this.telefono.match(/^[0-9]+$/) || this.telefono.length != 10){
                        return false;
                    }
                }
                return true;
            },
            validateImagen() {
                if(this.openValidations){
                    if(this.imagen == null || this.imagen.size > 3000000 || !this.imagen.name.match(/\.(png|jpg|jpeg)$/)){
                        return false;
                    }
                }
                return true;
            },
            submitForm() {
                this.openValidations = true;
                if(this.validateNombres() && this.validatePaterno() && this.validateCodigoPostal() && this.validateCalle() && this.validateNumero() && this.validateCiudad() && this.validateFechaNacimiento() && this.validateCorreoElectronico() && this.validateTelefono() && this.validateImagen()){
                    this.$swal({
                        icon: 'success',
                        title: 'Completado',
                        text: 'Formulario enviado correctamente',
                    });
                }else{
                    this.$swal({
                        icon: 'error',
                        title: 'Error',
                        text: 'Formulario con errores, por favor revisalo',
                    });
                }
            }
        }
    };

  </script>
    
                    