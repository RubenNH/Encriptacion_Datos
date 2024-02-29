<template>
    <div class="manage-place" style="margin-top: 20px;">
      <h1>Administrar lugar</h1>
      <form @submit.prevent="updatePlace">
        <label for="title">Título:</label>
        <input type="text" id="title" v-model="place.title" required>
        
        <label for="description">Descripción:</label>
        <textarea id="description" v-model="place.description" required></textarea>
        
        <label for="price">Precio por noche:</label>
        <input type="number" id="price" v-model.number="place.price" required>
        
        <label for="address">Dirección:</label>
        <input type="text" id="address" v-model="place.address" required>
        
        <label for="type">Tipo de vivienda:</label>
        <input type="text" id="type" v-model="place.type">
        
        <label for="photos">Fotos:</label>
        <input type="file" id="photos" multiple @change="handleFileUpload">
        
        <button type="submit">Actualizar</button>
      </form>
      <div class="preview">
        <div v-for="(photo, index) in place.photos" :key="index">
          <img :src="photo" alt="Photo Preview">
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        place: {
          title: 'Nombre del lugar',
          description: 'Descripción del lugar',
          price: 0,
          address: 'Dirección del lugar',
          type: 'Vivienda/departamento',
          photos: ['https://via.placeholder.com/150'] // Placeholder image URL
        }
      };
    },
    methods: {
      updatePlace() {
        
        console.log('Lugar actualizado:', this.place);
        
      },
      handleFileUpload(event) {
        const files = event.target.files;
      
        for (let i = 0; i < files.length; i++) {
          const file = files[i];
    
          const fileURL = URL.createObjectURL(file);
        
          this.place.photos.push(fileURL);
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .manage-place {
    max-width: 600px;
    margin: 20px auto; /* Margin-top ajustado */
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  h1 {
    margin-bottom: 20px;
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
  