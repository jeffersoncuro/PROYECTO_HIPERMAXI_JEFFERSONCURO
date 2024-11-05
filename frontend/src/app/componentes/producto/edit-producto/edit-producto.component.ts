import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { ProductoService } from '../../../servicio/producto.service';
import { Producto } from '../../../modelos/Producto';

@Component({
  selector: 'app-edit-producto',
  standalone: true,
  imports: [CommonModule, RouterOutlet,RouterModule,FormsModule],
  templateUrl: './edit-producto.component.html',
  styleUrl: './edit-producto.component.css'
})
export class EditProductoComponent {

  constructor(private router:Router, private productoService:ProductoService){}
  
  regProducto = new Producto();
  
  ngOnInit(): void {
    this.editar();
  }

  editar(){
      let id= JSON.parse(localStorage.getItem('id') as string);
      this.productoService.getProductoId(id).subscribe(data=>{
       this.regProducto=data;
    });
   }

   actualizar(producto:Producto){
      this.productoService.updateProducto(producto).subscribe(data=>{
          this.regProducto=data; 
          this.router.navigate(['productos']);
      });
   }

}
