import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionModeloComponent } from './gestion-modelo.component';

describe('GestionModeloComponent', () => {
  let component: GestionModeloComponent;
  let fixture: ComponentFixture<GestionModeloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionModeloComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionModeloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
