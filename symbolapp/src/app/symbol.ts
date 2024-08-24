export interface Symbol {
  id: number;
  type: string;
  size?: number;
  rotation?: number;
  fill?: string | null;
  fillopacity?: number;
  stroke?: string | null;
  strokeopacity?: number;
  strokewidth?: number;
  stokelinecap?: CanvasLineCap | null;
  strokedasharray?: string | null;
  strokelinejoinfill?: string | null;
  strokelinejoinstroke?: string | null;
  symbol?: string | null;
  name: string;
}
