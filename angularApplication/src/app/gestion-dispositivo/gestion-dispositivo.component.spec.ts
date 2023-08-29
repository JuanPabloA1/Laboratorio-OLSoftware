import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionDispositivoComponent } from './gestion-dispositivo.component';

describe('GestionDispositivoComponent', () => {
  let component: GestionDispositivoComponent;
  let fixture: ComponentFixture<GestionDispositivoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionDispositivoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionDispositivoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
