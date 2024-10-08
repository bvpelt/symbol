import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SymbolDetailComponent } from './symbol-detail.component';

describe('SymbolDetailComponent', () => {
  let component: SymbolDetailComponent;
  let fixture: ComponentFixture<SymbolDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SymbolDetailComponent]
    });
    fixture = TestBed.createComponent(SymbolDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
