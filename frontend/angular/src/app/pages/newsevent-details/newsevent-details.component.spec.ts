import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewseventDetailsComponent } from './newsevent-details.component';

describe('NewseventDetailsComponent', () => {
  let component: NewseventDetailsComponent;
  let fixture: ComponentFixture<NewseventDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewseventDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewseventDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
