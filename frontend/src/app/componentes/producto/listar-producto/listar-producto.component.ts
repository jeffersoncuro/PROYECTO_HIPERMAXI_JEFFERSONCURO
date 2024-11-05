import { Component } from '@angular/core';
import { Producto } from '../../../modelos/Producto';
import { ProductoService } from '../../../servicio/producto.service';
import { error } from 'console';
import { CommonModule } from '@angular/common';
import {Router, RouterModule, RouterOutlet } from '@angular/router';
 

@Component({
  selector: 'app-listar-producto',
  standalone: true,
  imports: [CommonModule, RouterOutlet,RouterModule],
  templateUrl: './listar-producto.component.html',
  styleUrl: './listar-producto.component.css'
})
export class ListarProductoComponent {

  productos? : Producto[];

  constructor(private productoService:ProductoService, private router:Router){}

  ngOnInit():void{
     this.productoService.getProductos().subscribe(
      data=>{
        this.productos=data;
      },
      error=>{
        console.log(error);
        
      }
     )
  }

  nuevo():void{
    this.router.navigate(['nuevoProducto']);
  }
  editar(producto:Producto):void{
    localStorage.setItem("id",producto.id.toString()); 
     this.router.navigate(['editarProducto']);
  }
  eliminar(producto:Producto):void{
    this.productoService.deleteProducto(producto).subscribe(data=>{
      this.productos=this.productos!.filter(p=>p!==producto);
    });
  }
}
