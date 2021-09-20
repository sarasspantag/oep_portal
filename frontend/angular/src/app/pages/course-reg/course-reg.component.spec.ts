import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseRegComponent } from './course-reg.component';

describe('CourseRegComponent', () => {
  let component: CourseRegComponent;
  let fixture: ComponentFixture<CourseRegComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseRegComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseRegComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
