import { Component, Input, OnInit, OnChanges, AfterViewInit } from '@angular/core';
import { Symbol } from '../symbol';

@Component({
  selector: 'app-symbol-detail',
  templateUrl: './symbol-detail.component.html',
  styleUrls: ['./symbol-detail.component.css']
})
export class SymbolDetailComponent implements OnInit, AfterViewInit, OnChanges {
  @Input() symbol?: Symbol;
  canvas?: HTMLCanvasElement;
  ctx?: CanvasRenderingContext2D | null;

  ngOnInit() {
    //console.log('OnInit symbol: ', this.symbol);
  }

  ngAfterViewInit() {
    //console.log('AfterviewInit symbol: ', this.symbol);
    this.canvas = document.getElementById('canvas1') as HTMLCanvasElement;
    this.ctx = this.canvas.getContext('2d');

    this.canvas.width = 100;
    this.canvas.height = 100;

    this.ngOnChanges();
  }

  ngOnChanges() {
    //console.log('change ctx: ', this.ctx);
    if (this.ctx != null) {
      this.ctx.clearRect(0, 0, this.canvas!.width, this.canvas!.height);

      // setup context
      if (typeof (this.symbol) != "undefined") {
        this.ctx.save();
        if ((typeof (this.symbol.fill) != "undefined") && this.symbol.fill) this.ctx.fillStyle = this.symbol.fill!;
        if ((typeof (this.symbol.fillopacity) != "undefined") && this.symbol.fillopacity) this.ctx.globalAlpha = this.symbol.fillopacity!;
        if ((typeof (this.symbol.stroke) != "undefined") && this.symbol.stroke) this.ctx.strokeStyle = this.symbol.stroke!;
        if ((typeof (this.symbol.strokeopacity) != "undefined") && this.symbol.strokeopacity) this.ctx.globalAlpha = this.symbol.strokeopacity!;
        if ((typeof (this.symbol.strokewidth) != "undefined") && this.symbol.strokewidth! > 0) {
          this.ctx.lineWidth = this.symbol.strokewidth!;
        }
        if ((typeof (this.symbol.stokelinecap) != "undefined") && this.symbol.stokelinecap) this.ctx.lineCap = this.symbol.stokelinecap;
        if ((typeof (this.symbol.strokedasharray) != "undefined") && this.symbol.strokedasharray) {
          var dasharray: number[] = [];
          var dashstrings: string[] = this.symbol.strokedasharray!.split(/\s/);

          dashstrings.forEach(dash => {
            dasharray.push(Number(dash));
          });
          this.ctx.setLineDash(dasharray);
        }
        // setup context

        // draw symbol
        if (this.symbol.type === "Punt") this.drawPoint(this.symbol, this.canvas!, this.ctx);
        if (this.symbol.type === "Lijn") this.drawLijn(this.symbol, this.canvas!, this.ctx);
        if (this.symbol.type === "Vlak") this.drawVlak(this.symbol, this.canvas!, this.ctx);
        if (this.symbol.type === "Vlak met transparantie met een gesloten lijn") this.drawVlak(this.symbol, this.canvas!, this.ctx);
        // draw symbol
        this.ctx.restore();
      }
    }
  }

  drawPoint(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    if ((typeof (symbol.rotation) != "undefined") && symbol.rotation > 0) {
      ctx.translate(canvas.width / 2, 0); //canvas.height/2)
      ctx.rotate(this.angleToRadians(symbol.rotation));
      ctx.scale(0.5, 0.5);
    }
    switch (symbol.welknownname) {
      case "circle":
        this.drawCircle(symbol, canvas, ctx);
        break;
      case "square":
        this.drawSquare(symbol, canvas, ctx);
        break;
      case "cross_fill":
        this.drawCross(symbol, canvas, ctx);
        break;
      case "triangle":
        this.drawTriangle(symbol, canvas, ctx);
        break;
      case "star":
        this.drawStar(symbol, canvas, ctx);
        break;
      default:
        console.log("Unknown type");
        break;
    }
  }

  private drawCircle(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    ctx.beginPath();
    ctx.arc(canvas.width / 2, canvas.height / 2, Math.min(canvas.width / 2, canvas.height / 2) * 0.8, 0, 2 * Math.PI);
    ctx.stroke();
    ctx.fill();
    ctx.closePath();
  }

  private drawSquare(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    ctx.beginPath();
    ctx.rect(0, 0, canvas.width, canvas.height);
    ctx.stroke();
    ctx.fill();
    ctx.closePath();
  }

  private drawCross(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    ctx.strokeStyle = ctx.fillStyle;
    ctx.beginPath();
    ctx.moveTo(canvas.width / 2, 0);
    ctx.lineTo(canvas.width / 2, canvas.height);
    ctx.moveTo(0, canvas.height / 2);
    ctx.lineTo(canvas.width, canvas.height / 2);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();
  }

  private drawTriangle(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    var path1 = new Path2D();

    ctx.beginPath();
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
  private drawStar(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    var radius_outside = Math.min(canvas.width / 2, canvas.height / 2);
    var radius_inside = radius_outside * 0.5;
    var path1 = new Path2D();

    var r = 0;
    var [x, y] = [0, 0];
    var [xstart, ystart] = [canvas.width / 2, canvas.height / 2];

    ctx.beginPath();
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

  private drawLijn(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {
    const offset: number = 10;
    const ypos = canvas.width / 2;
    /*
    if (symbol.name.substring(0,3) === 'lth') {
      console.log('name: ', symbol.name, ' substring: ', symbol.name.substring(0,3));
      ctx.save();
      ctx.lineWidth = 1;
      ctx.strokeStyle ='#000000';
      ctx.setLineDash([]);
      ctx.beginPath();
      ctx.moveTo(offset, ypos);
      ctx.lineTo(canvas.width - offset, ypos);
      ctx.closePath();
      ctx.stroke();
      ctx.restore();
    }
    */
    ctx.beginPath();
    ctx.moveTo(offset, ypos);
    ctx.lineTo(canvas.width - offset, ypos);
    ctx.closePath();
    ctx.stroke();
  }

  private drawVlak(symbol: Symbol, canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D): void {

    if ((typeof (symbol.stroke) != "undefined") && symbol.stroke) ctx.fillStyle = symbol.stroke!;

    ctx.beginPath();
    ctx.rect(0, 0, canvas.width, canvas.height);
    ctx.fillRect(0, 0, canvas.width, canvas.height);
    ctx.stroke();
    ctx.fill();
  }

  private starCoordsOut(index: number, radius: number): [x: number, y: number] {
    var x: number;
    var y: number;
    var angle: number;

    angle = 2 * Math.PI * index / 5 - Math.PI / 10;

    x = radius * Math.cos(angle);
    y = radius * Math.sin(angle);

    return [x, y];
  }

  private starCoordsIn(index: number, radius: number): [x: number, y: number] {
    var x: number;
    var y: number;
    var angle: number;

    angle = 4 * Math.PI * (index + 0.5) / 10 - Math.PI / 10;

    x = radius * Math.cos(angle);
    y = radius * Math.sin(angle);

    return [x, y];
  }

  private angleToRadians(angle: number): number {
    return Math.PI * angle / 180;
  }
}
