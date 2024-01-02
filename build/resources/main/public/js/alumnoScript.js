
  

  const app = Vue.createApp({
    data() {
      return {
        alumno: {},
        materias: {},
        materiasNoCargadas: {}
      
      };
    },
    methods:{

      async updateData(){
        try {
          const respuestaAlumno = await fetch("/alumno/Get")
          const alumno = await respuestaAlumno.json()

          const respuestaMateriasNoCargadas = await fetch("/alumno/GetMateriasNoCargadas")
          const materiasNoCargadas = await respuestaMateriasNoCargadas.json()
          
          this.alumno = alumno
          this.materias = this.alumno.materias
          this.materiasNoCargadas = materiasNoCargadas
          
          console.log("MATERIAS NO CARGADAS")
          console.log(materiasNoCargadas)
        } catch (error) {
          console.log(error)
        }
      },
      async cargarMateria(id){
        const response = await fetch(`/alumno/AgregarMateria/${id}`, {
          method: 'PATCH',
          headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken // Include the CSRF token in the headers
          }})

        if(response.ok){
          console.log("Materia cargada")
          this.updateData()
        } 
        else console.log("Error al cargar materia")

    
      },

      async eliminarMateria(id){
        const response = await fetch(`/alumno/QuitarMateria/${id}`, 
        {method: 'PATCH',    
        headers: {
          'Content-Type': 'application/json',
          'X-CSRF-TOKEN': csrfToken // Include the CSRF token in the headers
        }})

        if(response.ok){
          console.log("Materia eliminada")
          this.updateData()
        }
        else{
          console.log("Error al eliminar Materia")
        } 
      },

      
    },    async created() {
      this.updateData()
    
    }
  });
  
  // Mount the Vue app to the specified element in the HTML
  app.mount('#vueContainer');
  