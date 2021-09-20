import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OnlineexamineportalComponent } from './onlineexamineportal.component';

describe('OnlineexamineportalComponent', () => {
  let component: OnlineexamineportalComponent;
  let fixture: ComponentFixture<OnlineexamineportalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OnlineexamineportalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OnlineexamineportalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
