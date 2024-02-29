<template>
    <div class="profile" style="margin-top: 20px;">
      
      <div class="profile-content">
        <div class="profile-header">
          <img :src="seller.photo" alt="Profile Picture" class="profile-picture">
          
          <input type="file" @change="changeProfilePicture">
          <h1>{{ seller.firstName }} {{ seller.lastName }}</h1>
          <p>{{ seller.bio }}</p>
        </div>
        <div class="profile-details">
          <h2>Contact Information</h2>
          <p>Email: {{ seller.email }}</p>
          <p>Phone: {{ seller.phone }}</p>
          <p>Address: {{ seller.address }}</p>
          
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        seller: {
          firstName: 'Emiliano',
          lastName: 'Vargas',
          bio: 'Departamento ',
          email: '20203tn061@utez.edu.mx',
          phone: '7774982546',
          address: 'Cuernavaca',
          photo: 'https://via.placeholder.com/150' 
        }
      };
    },
    methods: {
      changeProfilePicture(event) {
        const file = event.target.files[0];
        const reader = new FileReader();
        reader.onload = () => {
          this.seller.photo = reader.result;
        };
        if (file) {
          reader.readAsDataURL(file);
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .profile {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    display: flex;
  }
  
  .profile-content {
    flex-grow: 1;
  }
  
  .profile-header {
    text-align: center;
  }
  
  .profile-picture {
    border-radius: 50%;
    max-width: 150px;
    max-height: 150px;
  }
  
  .profile-details {
    margin-top: 20px;
  }
  
  .profile-details h2 {
    margin-bottom: 10px;
  }
  
  textarea {
    width: 100%;
    margin-bottom: 10px;
  }
  
  button {
    display: block;
    margin: 0 auto;
  }
  </style>
  