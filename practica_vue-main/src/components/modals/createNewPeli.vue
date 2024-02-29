<template>
  <b-modal id="modalon" title="Registro de películas" hide-footer>
    <b-form @submit="onSubmit2" @reset="onReset">
      <b-form-group id="input-group-1" label="Nombre de película:" label-for="input-1"
        description="Registra el nombre latinoamericano.">
        <b-form-input id="input-1" v-model="form.name" type="text" placeholder="Ingresa el nombre de la película."
          required></b-form-input>
      </b-form-group>

      <b-form-input id="input-2" v-model="form.year" type="number" required
        placeholder="Ingresa el año de la película"></b-form-input>

      <b-form-group id="input-group-4" label="Género" label-for="input-3" class="mb-2">
        <b-form-select v-model="form.genre" :options="genres" required style="width: 100%">
          <template v-slot:first>
            <option :value="0">Selecciona un género</option>
          </template>
        </b-form-select>
        <div v-if="genres.length === 0">Loading genres...</div>
      </b-form-group>
      <b-form-input type="text" v-model="form.director" class="mb-2" required
        placeholder="Ingresa el nombre del director"></b-form-input>
      <b-datepicker id="input-3" v-model="form.publishDate" class="mb-2" required
        placeholder="Ingresa la fecha de estreno"></b-datepicker>
      <b-button type="submit" variant="primary">Registrar</b-button>

    </b-form>
  </b-modal>
</template>
  
<script>
import axios from 'axios';
export default {
  props: {
    genres: Array,
    genresToSend: Array,
    movies: Array,
  },
  data() {
    return {
      fields: [
        { key: 'name', label: 'Nombre', sortable: true },
        { key: 'genre', label: 'Genero', sortable: true },
        { key: 'year', label: 'Año', sortable: true },
        { key: 'director', label: 'Director', sortable: true },
        { key: 'publishDate', label: 'Fecha de publicación', sortable: true },
      ],
      form: {
        name: '',
        genre: '',
        year: '',
        director: '',
        publishDate: '',
      },
    };
  },
  methods: {
    onSubmit2(evt) {
      evt.preventDefault();
      this.$emit('onSubmit2', this.form);

      this.registerMovie();
    },
    onReset(evt) {
      evt.preventDefault();
      this.form = {
        name: '',
        genre: '',
        year: '',
        director: '',
        publishDate: '',
      };
    },
    async registerMovie() {
      try {
        if (this.form.name && this.form.genre && this.form.year) {
          console.log("ey")

          this.form.genre = this.genresToSend.find(genre => genre.id === this.form.genre);

          // Enviar los datos al servidor
          await axios.post('http://localhost:8080/api/movies/movies', {
            name: this.form.name,
            genre: this.form.genre,
            year: this.form.year,
            director: this.form.director,
            publishDate: this.form.publishDate,
          });
          this.$emit('movieRegistered');
          alert('¡Película registrada con éxito!');
        };
      } catch (error) {
        console.error('Error al registrar la película:', error);
      }
    },

  },
  mounted() {
    this.form.genre = 0;
  },

};
</script>
  
<style scoped>/* Estilos adicionales según sea necesario */</style>
  
