
import { Component, OnInit, ViewChild } from '@angular/core';
import { Symbol } from '../symbol';
//import { Canvas } from '@angular/cdk/platform'; // Import Canvas class
//import { Canvas } from '@angular/cdk/platform';

@Component({
  selector: 'app-symbols',
  templateUrl: './symbols.component.html',
  styleUrls: ['./symbols.component.css']
})
export class SymbolsComponent implements OnInit {
  @ViewChild('canvas') canvasRef: any ; // Use ViewChild to get reference
/*
  symbol: Symbol = {
    id: 2321,
    type: "Punt",
    size: 0,
    rotation: 0,
    fill: "#ffc8be",
    fillopacity: 1,
    stroke: "#999999",
    strokeopacity: 0,
    strokewidth: 1,
    name: "px005"
  };
*/
  symbol: Symbol = {
    id: 2412,
    type: "Lijn",
    size: 0,
    rotation: 0,
    fill: null,
    fillopacity: 0,
    stroke: "#b45fd2",
    strokeopacity: 1,
    strokewidth: 8,
    stokelinecap: "butt",
    strokedasharray: null,
    strokelinejoinfill: null,
    strokelinejoinstroke: null,
    symbol: null,
    name: "lt003"
  }

  ngOnInit() {

  }

  ngAfterViewInit() {
    const canvas = document.getElementById('canvas1') as HTMLCanvasElement;
    var ctx: CanvasRenderingContext2D | null = canvas.getContext('2d');

    canvas.width=100;
    canvas.height=100;


    if (ctx != null) {
      /*
      // Draw a rectangle
      if (this.symbol.fill) ctx.fillStyle = this.symbol.fill!;
      if (this.symbol.fillopacity) ctx.globalAlpha = this.symbol.fillopacity!;
      ctx.strokeStyle =  "#999999";
      ctx.fillRect(0, 0, canvas.width, canvas.height);
      ctx.strokeRect(0, 0, canvas.width, canvas.height);
      */
      if (this.symbol.type === "Punt") this.drawPoint(canvas, ctx);
      if (this.symbol.type === "Lijn") this.drawLijn(canvas, ctx);
    } else {
      console.log('ctx is null');
    }
  }

  drawPoint(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    ctx.beginPath();
    ctx.arc(canvas.width/2, canvas.height/2, Math.min(canvas.width/2, canvas.height/2) * 0.8, 0, 2 * Math.PI);
    if (this.symbol.fill) ctx.fillStyle = this.symbol.fill!;
    if (this.symbol.fillopacity) ctx.globalAlpha = this.symbol.fillopacity!;
    if (this.symbol.stroke) ctx.strokeStyle = this.symbol.stroke!;
    ctx.fill();
  }

  drawLijn(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    if (this.symbol.fillopacity) ctx.globalAlpha = this.symbol.fillopacity!;
    if (this.symbol.stroke) ctx.strokeStyle = this.symbol.stroke!;
    if (this.symbol.strokeopacity) ctx.globalAlpha = this.symbol.strokeopacity!;
    if (this.symbol.stokelinecap) ctx.lineCap = this.symbol.stokelinecap;
 //   if (this.symbol.strokedasharray)  ctx.setLineDash = this.symbol.strokedasharray!;
    ctx.beginPath();
    ctx.moveTo(10,50);
    ctx.lineTo(canvas.width - 20, 50);
    ctx.stroke();
  }

}
