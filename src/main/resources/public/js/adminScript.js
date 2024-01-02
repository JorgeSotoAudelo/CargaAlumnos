
  

  const app = Vue.createApp({
    data() {
      return {
        alumnos: {},
        materias: {},
        Alumno:{
          nombre: '',
          correo: '',
          contrasenia: '',
          materias:[]
        },
        Materia:{
          nombre: ''
        }
      };
    },
    methods:{

      async updateData(){
        try {
            const respuestaAlumnos = await fetch("/admin/GetAlumnos")
            const alumnos = await respuestaAlumnos.json()

            const respuestaMaterias = await fetch("/admin/GetMaterias")
            const materias = await respuestaMaterias.json() 

            this.alumnos = alumnos
            this.materias = materias
        } catch (error) {
          console.log(error)
        }
      },
      async AgregarAlumno(){
        const response = await fetch(`/admin/AgregarAlumno`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken // Include the CSRF token in the headers
          },
        body:JSON.stringify(this.Alumno)})

        if(response.ok){
          console.log("Alumno Agregado")
          this.updateData()
        } 
        else console.log("Error al cargar materia")

    
      },
      async AgregarMateria(){
        const response = await fetch(`/admin/AgregarMateria`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken // Include the CSRF token in the headers
          },
        body:JSON.stringify(this.Materia)})

        if(response.ok){
          console.log("Materia Agregada")
          this.updateData()
        } 
        else console.log("Error al cargar materia")

    
      },

      async eliminarMateria(id){
        const response = await fetch(`/admin/EliminarMateria/${id}`, 
        {method: 'DELETE',    
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
      async eliminarAlumno(id){
        const response = await fetch(`/admin/EliminarAlumno/${id}`, 
        {method: 'DELETE',    
        headers: {
          'Content-Type': 'application/json',
          'X-CSRF-TOKEN': csrfToken // Include the CSRF token in the headers
        }})

        if(response.ok){
          console.log("Alumno eliminado")
          this.updateData()
        }
        else{
          console.log("Error al eliminar Alumno")
        } 
      }

      
    },    async created() {
      this.updateData()
    
    }
  });
  
  // Mount the Vue app to the specified element in the HTML
  app.mount('#vueContainer');
  