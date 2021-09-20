import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GalleryformComponent } from './galleryform.component';

describe('GalleryformComponent', () => {
  let component: GalleryformComponent;
  let fixture: ComponentFixture<GalleryformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GalleryformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GalleryformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
