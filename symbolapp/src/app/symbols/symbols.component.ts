
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
  @ViewChild('canvas') canvasRef: any; // Use ViewChild to get reference
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
    "id": 7962,
    "type": "Punt",
    "size": 24,
    "rotation": 0,
    "fill": "#b45fd2",
    "fillopacity": 1,
    "stroke": "#999999",
    "strokeopacity": 0,
    "strokewidth": 1,
    "stokelinecap": null,
    "strokedasharray": null,
    "strokelinejoinfill": null,
    "strokelinejoinstroke": null,
    "symbol": null,
    "name": "ps003",
    "welknownname": "star"
  }

  ngOnInit() {

  }

  ngAfterViewInit() {
    const canvas = document.getElementById('canvas1') as HTMLCanvasElement;
    var ctx: CanvasRenderingContext2D | null = canvas.getContext('2d');

    canvas.width = 100;
    canvas.height = 100;


    if (ctx != null) {
      if (this.symbol.type === "Punt") this.drawPoint(canvas, ctx);
      if (this.symbol.type === "Lijn") this.drawLijn(canvas, ctx);
    } else {
      console.log('ctx is null');
    }
  }

  drawPoint(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    if (this.symbol.welknownname === "circle") {
      this.drawCircle(canvas, ctx);
    }
    if (this.symbol.welknownname === "square") {
      this.drawSquare(canvas, ctx);
    }
    if (this.symbol.welknownname === "cross_fill") {
      this.drawCross(canvas, ctx);
    }
    if (this.symbol.welknownname === "triangle") {
      this.drawTriangle(canvas, ctx);
    }
    if (this.symbol.welknownname === "star") {
      this.drawStar(canvas, ctx);
    }
  }

  private drawCircle(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    ctx.beginPath();
    ctx.arc(canvas.width / 2, canvas.height / 2, Math.min(canvas.width / 2, canvas.height / 2) * 0.8, 0, 2 * Math.PI);
    if (this.symbol.fill) ctx.fillStyle = this.symbol.fill!;
    if (this.symbol.fillopacity) ctx.globalAlpha = this.symbol.fillopacity!;
    if (this.symbol.stroke) ctx.strokeStyle = this.symbol.stroke!;
    if ((typeof (this.symbol.strokewidth) != "undefined") && (this.symbol.strokewidth > 0)) ctx.lineWidth = this.symbol.strokewidth!;
    ctx.stroke();
    ctx.fill();
    ctx.closePath();
  }

  private drawSquare(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    ctx.beginPath();
    ctx.rect(0, 0, canvas.width, canvas.height);
    if (this.symbol.fill) ctx.fillStyle = this.symbol.fill!;
    if (this.symbol.fillopacity) ctx.globalAlpha = this.symbol.fillopacity!;
    if (this.symbol.stroke) ctx.strokeStyle = this.symbol.stroke!;
    if ((typeof (this.symbol.strokewidth) != "undefined") && (this.symbol.strokewidth > 0)) ctx.lineWidth = this.symbol.strokewidth!;
    ctx.stroke();
    ctx.fill();
    ctx.closePath();
  }

  private drawCross(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    ctx.beginPath();
    if (this.symbol.fill) ctx.fillStyle = this.symbol.fill!;
    if (this.symbol.fillopacity) ctx.globalAlpha = this.symbol.fillopacity!;
    if (this.symbol.stroke) ctx.strokeStyle = this.symbol.stroke!;
    if ((typeof (this.symbol.strokewidth) != "undefined") && (this.symbol.strokewidth > 0)) ctx.lineWidth = this.symbol.strokewidth!;
    ctx.moveTo(canvas.width / 2, 0);
    ctx.lineTo(canvas.width / 2, canvas.height);
    ctx.moveTo(0, canvas.height / 2);
    ctx.lineTo(canvas.width, canvas.height / 2);
    ctx.stroke();
    ctx.closePath();
  }

  private drawTriangle(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    ctx.beginPath();
    if (this.symbol.fill) ctx.fillStyle = this.symbol.fill!;
    if (this.symbol.fillopacity) ctx.globalAlpha = this.symbol.fillopacity!;
    if (this.symbol.stroke) ctx.strokeStyle = this.symbol.stroke!;
    if ((typeof (this.symbol.strokewidth) != "undefined") && (this.symbol.strokewidth > 0)) ctx.lineWidth = this.symbol.strokewidth!;

    var path1 = new Path2D();
    path1.moveTo(canvas.width / 2, 0);
    path1.lineTo(canvas.width, canvas.height);
    path1.lineTo(0, canvas.height);
    path1.lineTo(canvas.width / 2, 0);

    ctx.fill(path1);
    ctx.closePath();
  }

  /*
  see https://math.stackexchange.com/questions/3582342/coordinates-of-the-vertices-of-a-five-pointed-star
  */
  private drawStar(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    ctx.beginPath();
    if (this.symbol.fill) ctx.fillStyle = this.symbol.fill!;
    if (this.symbol.fillopacity) ctx.globalAlpha = this.symbol.fillopacity!;
    if (this.symbol.stroke) ctx.strokeStyle = this.symbol.stroke!;
    if ((typeof (this.symbol.strokewidth) != "undefined") && (this.symbol.strokewidth > 0)) ctx.lineWidth = this.symbol.strokewidth!;

    var radius_outside = Math.min(canvas.width / 2, canvas.height / 2);
    var radius_inside = radius_outside * 0.5;
    var path1 = new Path2D();

    var r = 0;
    var [x, y] = [0, 0];
    var [xstart, ystart] = [canvas.width / 2, canvas.height / 2];

    for (let i = 0; i < 5; i++) {
      // big ring
      [x, y] = this.starCoordsOut(i, radius_outside);
      if (i == 0) {
        path1.moveTo(x + xstart, y + ystart);
      } else {
        path1.lineTo(x + xstart, y + ystart);
      }
      // small ring
      [x, y] = this.starCoordsIn(i, radius_inside);
      path1.lineTo(x + xstart, y + ystart);
    }
    [x, y] = this.starCoordsOut(0, radius_outside);
    path1.lineTo(x + xstart, y + ystart);

    ctx.fill(path1);
    ctx.closePath();
  }

  private drawLijn(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    if (this.symbol.fillopacity) ctx.globalAlpha = this.symbol.fillopacity!;
    if (this.symbol.stroke) ctx.strokeStyle = this.symbol.stroke!;
    if (this.symbol.strokeopacity) ctx.globalAlpha = this.symbol.strokeopacity!;
    if (this.symbol.stokelinecap) ctx.lineCap = this.symbol.stokelinecap;
    //   if (this.symbol.strokedasharray)  ctx.setLineDash = this.symbol.strokedasharray!;
    ctx.beginPath();
    ctx.moveTo(10, 50);
    ctx.lineTo(canvas.width - 20, 50);
    ctx.stroke();
  }

  private starCoordsOut(index: number, radius: number): [x: number, y: number] {
    var x: number;
    var y: number;
    var angle: number;

    angle = 2 * Math.PI * index / 5 - Math.PI/10;

    x = radius * Math.cos(angle);
    y = radius * Math.sin(angle);

    console.log("out - index: " + index + " x: " + x + " y: " + y);
    return [x, y];
  }

  private starCoordsIn(index: number, radius: number): [x: number, y: number] {
    var x: number;
    var y: number;
    var angle: number;

    angle = 4 * Math.PI * (index + 0.5) / 10 - Math.PI/10;

    x = radius * Math.cos(angle);
    y = radius * Math.sin(angle);

    console.log("in  - index: " + index + " x: " + x + " y: " + y);
    return [x, y];
  }

}
