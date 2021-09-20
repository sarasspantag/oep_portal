import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AluminiblogdetailsComponent } from './aluminiblogdetails.component';

describe('AluminiblogdetailsComponent', () => {
  let component: AluminiblogdetailsComponent;
  let fixture: ComponentFixture<AluminiblogdetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AluminiblogdetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AluminiblogdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
