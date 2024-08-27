
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

  drawCircle(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
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

  drawSquare(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
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

  drawCross(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
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

  drawTriangle(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
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

  drawStar(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    ctx.beginPath();
    if (this.symbol.fill) ctx.fillStyle = this.symbol.fill!;
    if (this.symbol.fillopacity) ctx.globalAlpha = this.symbol.fillopacity!;
    if (this.symbol.stroke) ctx.strokeStyle = this.symbol.stroke!;
    if ((typeof (this.symbol.strokewidth) != "undefined") && (this.symbol.strokewidth > 0)) ctx.lineWidth = this.symbol.strokewidth!;

    var x = canvas.width / 2;
    var y = canvas.height / 2;
    var radius_outside = Math.min(canvas.width / 2, canvas.height / 2);
    var radius_inside = radius_outside * 0.5;

    var path1 = new Path2D();

    var angle: number;
    var angles: number[] = [18, 54, 90, 126, 162, 198, 234, 270, 306, 342];
    var index = 0;
    var r = 0;
    path1.moveTo(x + radius_outside * Math.cos(angles[0]), y + radius_outside * Math.sin(angles[0]));
    angles.forEach(angle => {
      if (index % 2 === 0) {
        r = radius_outside
      } else {
        r = radius_inside;
      }
      angle = this.angleToRadians(45);
      path1.lineTo(x + r * Math.cos(angle), y + r * Math.sin(angle));
    })
    ctx.fill(path1);
    ctx.closePath();
  }

  drawLijn(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
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

  angleToRadians(angle: number): number {
    return Math.PI * angle / 360;
  }

}
