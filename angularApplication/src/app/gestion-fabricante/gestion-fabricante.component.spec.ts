import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionFabricanteComponent } from './gestion-fabricante.component';

describe('GestionFabricanteComponent', () => {
  let component: GestionFabricanteComponent;
  let fixture: ComponentFixture<GestionFabricanteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionFabricanteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionFabricanteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
