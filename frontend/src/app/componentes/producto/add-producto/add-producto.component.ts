import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { ProductoService } from '../../../servicio/producto.service';
import { Producto } from '../../../modelos/Producto';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-producto',
  standalone: true,
  imports: [CommonModule, RouterOutlet,RouterModule,FormsModule],
  templateUrl: './add-producto.component.html',
  styleUrl: './add-producto.component.css'
})
export class AddProductoComponent {

  constructor(private router:Router, private productoService:ProductoService){}
  
  regProducto = new Producto();
 
  guardar(producto:Producto){
  this.productoService.createProducto(producto).subscribe(
    data=>{
      this.router.navigate(['productos']);
    }
  )
  }

}
