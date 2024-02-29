<template>
  <b-container fluid>
    <b-row>
      <createNewPeli :genres="genres" :genresToSend="genresToSend" :movies="movies" @movieRegistered="onMovieRegistered" />
      <b-button class="mt-3" size="lg" v-b-modal.modalon>Registrar película</b-button>
      <b-card>
        <b-card-header>
          <h3 class="text-center">Peliculas</h3>
        </b-card-header>
        <b-card-body>
          
          <!-- card where are gonna to show the filter options. search by name, directos, range of years, genres and by publish date -->
          <b-card>
            <b-card-body>
              <b-form @submit="onSubmit">
                <b-row>
                  <b-col cols="12" md="4">
                    <b-form-group label="Nombre">
                      <b-form-input v-model="seachForm.name" 
                      required
                      pattern="[A-Za-z]+"
                      />
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="4">
                    <b-form-group label="Director">
                      <b-form-input v-model="seachForm.director"
                      required
                      pattern="[A-Za-z]+"
                      />
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="4">
                    <b-form-group label="Genero">
                      <b-form-select v-model="seachForm.genre" :options="genres" style="width: 100%">
                        <template v-slot:first>
                          <option :value="0">Ninguna</option>
                        </template>
                      </b-form-select>
                    </b-form-group>
                  </b-col>
                </b-row>
                <b-row>
                  <b-col cols="12" md="4">
                    <b-form-group label="Fecha de publicación">
                      <b-datepicker v-model="seachForm.publishDate" required></b-datepicker>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="4">
                    <b-form-group label="Año Inicio">
                      <b-form-input v-model="seachForm.yearIni" type="number"
                      required
                      ></b-form-input>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="4">
                    <b-form-group label="Año Final">
                      <b-form-input v-model="seachForm.yearEnd" type="number"
                      required
                      ></b-form-input>
                    </b-form-group>
                  </b-col>
                  <b-col cols="12" md="12" class="mt-4">
                    <b-button type="submit" variant="primary" style="width: 100%;">Buscar</b-button>
                  </b-col>
                </b-row>
              </b-form>
            </b-card-body>
          </b-card>
          <b-table striped hover :items="movies" :fields="fields" />
        </b-card-body>
      </b-card>
    </b-row>
  </b-container>
</template>
    
<script>
import axios from 'axios';
import createNewPeli from './modals/createNewPeli.vue';
export default {
  components: { createNewPeli },
  data() {
    return {
      genres: [],
      genresToSend: [],
      movies: [],
      fiveYearsOnFuture: '',
      fields: [
        { key: 'name', label: 'Nombre', sortable: true },
        { key: 'genre', label: 'Genero', sortable: true },
        { key: 'year', label: 'Año', sortable: true },
        { key: 'director', label: 'Director', sortable: true },
        { key: 'publishDate', label: 'Fecha de publicación', sortable: true },
      ],
      seachForm: {
        name: '',
        director: '',
        yearIni: '',
        yearEnd: '',
        genre: '',
        publishDate: '',
      },
    };
  },
  mounted() {
    this.fetchGenres();
    this.fetchMovies();
  },
  methods: {
    async fetchGenres() {
      try {
        const response = await axios.get('http://localhost:8080/api/movies/genres');
        this.genres = response.data.map(genre => ({ text: genre.name, value: genre.id }));
        this.genresToSend = response.data;
      } catch (error) {
        console.error('Error al obtener los géneros:', error);
      }
    },
    async fetchMovies() {
      try {
        const response = await axios.get('http://localhost:8080/api/movies/movies');
        this.movies = response.data;
        this.movies.forEach(movie => {
          movie.genre = movie.genre.name;
        });
      } catch (error) {
        console.error('Error al obtener las películas:', error);
      }
    },
    async onSubmit(evt) {
      if(this.seachForm.yearIni > this.seachForm.yearEnd) {
        alert("El año de INICIO es mayor que el año FINAL, ERROR 400.");
        return false; 
      }
      evt.preventDefault();
      try {

        //search genre by the from and send the name of the genre
        if (this.seachForm.genre > 0) {
          this.seachForm.genre = this.genresToSend.find(genre => genre.id === this.seachForm.genre);
          this.seachForm.genre = this.seachForm.genre.name;
        }else{
          this.seachForm.genre = '';
        }
        if (this.seachForm.publishDate) {
          this.seachForm.publishDate = new Date(this.seachForm.publishDate).toISOString().split('T')[0];
          this.seachForm.publishDate = this.seachForm.publishDate.replace(/-/g, '/');
        }
        this.fiveYearsOnFuture = new Date();
        this.fiveYearsOnFuture.setFullYear(this.fiveYearsOnFuture.getFullYear() + 5);
        this.fiveYearsOnFuture = this.fiveYearsOnFuture.toISOString().split('T')[0];
        this.fiveYearsOnFuture = this.fiveYearsOnFuture.replace(/-/g, '/');

        const response = await axios.post('http://localhost:8080/api/movies/search', { 
          name: this.seachForm.name,
          director: this.seachForm.director,
          year1: this.seachForm.yearIni,
          year2: this.seachForm.yearEnd,
          genre: this.seachForm.genre,
          publishDate: this.seachForm.publishDate ? this.seachForm.publishDate : this.fiveYearsOnFuture,
        });
        this.movies = response.data;
        this.movies.forEach(movie => {
          movie.genre = movie.genre.name;
        });
      } catch (error) {
        console.error('Error al buscar las películas:', error);
      }
    },

    async onMovieRegistered() {
      // Reload the movies after a new movie is registered
      this.fetchMovies();
    },
  }
};
</script>
  
