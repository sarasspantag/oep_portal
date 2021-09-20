import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewfacultyProfiledetailsComponent } from './viewfaculty-profiledetails.component';

describe('ViewfacultyProfiledetailsComponent', () => {
  let component: ViewfacultyProfiledetailsComponent;
  let fixture: ComponentFixture<ViewfacultyProfiledetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewfacultyProfiledetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewfacultyProfiledetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
