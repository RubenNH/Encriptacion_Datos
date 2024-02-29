<template>
    <div class="register-place" style="margin-top: 20px;">
      <h1>Registrar un nuevo lugar</h1>
      <form @submit.prevent="registerPlace">
        <label for="title">Título:</label>
        <input type="text" id="title" v-model="newPlace.title" required>
        
        <label for="description">Descripción:</label>
        <textarea id="description" v-model="newPlace.description" required></textarea>
        
        <label for="price">Precio por noche:</label>
        <input type="number" id="price" v-model.number="newPlace.price" required>
        
        <label for="address">Dirección:</label>
        <input type="text" id="address" v-model="newPlace.address" required>
        
        <label for="type">Tipo de vivienda:</label>
        <input type="text" id="type" v-model="newPlace.type" required>

        <label for="photos">Fotos:</label>
        <input type="file" id="photos" multiple @change="handleFileUpload">
        
        <button type="submit">Registrar</button>
      </form>
      <div class="preview">
        <div v-for="(photo, index) in newPlace.photos" :key="index">
          <img :src="photo" alt="Photo Preview">
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        newPlace: {
          title: '',
          description: '',
          price: null,
          address: '',
          type:'',
          photos: [] 
        }
      };
    },
    methods: {
      registerPlace() {
      
        console.log('Nuevo lugar registrado:', this.newPlace);
      
      },
      handleFileUpload(event) {
        const files = event.target.files;
    
        for (let i = 0; i < files.length; i++) {
          const file = files[i];
          // Convertir el archivo a una URL de objeto (blob)
          const fileURL = URL.createObjectURL(file);
         
          this.newPlace.photos.push(fileURL);
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .register-place {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
  }
  
  input[type="text"],
  textarea,
  input[type="number"] {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  button {
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  
  .preview {
    margin-top: 20px;
    display: flex;
    flex-wrap: wrap;
  }
  
  .preview img {
    width: 100px;
    height: 100px;
    margin-right: 10px;
    margin-bottom: 10px;
    object-fit: cover;
  }
  </style>
  