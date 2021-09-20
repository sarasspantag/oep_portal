import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AluminiblogformComponent } from './aluminiblogform.component';

describe('AluminiblogformComponent', () => {
  let component: AluminiblogformComponent;
  let fixture: ComponentFixture<AluminiblogformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AluminiblogformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AluminiblogformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
