import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { debounceTime } from 'rxjs/operators'

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {
  successSubmit:boolean = false;
  isValid:boolean = true;
  rounds: any = [];
  userForm: FormGroup;
  originalUser: any;
  isEditing: boolean = false;
  // addUserForm: FormGroup;
  errors: any = {};
  constructor(private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute, ) { }

  ngOnInit() {

    this.userService.getRounds().subscribe(item => {
      this.rounds = item;
      console.log(this.rounds);
    });

    this.userForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', [Validators.required,Validators.maxLength(10)]],
      roundNumber: ['', Validators.required],
    });

    // let emailChecker = this.userForm.get('email')
    // emailChecker.statusChanges
    //   .pipe(debounceTime(2000))
    //   .subscribe(e => {
    //     if (e === 'INVALID') {
    //       let errors = emailChecker.errors;
    //       if (errors.required) {
    //         this.errors['email'] = "Email is required";
    //       }
    //       if (errors.email) {
    //         this.errors['email'] = "Invalid Email";
    //       }
    //     }
    //     else {
    //       this.errors['email'] = '';
    //     }

    //   });


    this.route.params.subscribe(routeParam => {
      let id = routeParam['userId'];
      if (id) {

        this.userService.loadUserById(id)
          .subscribe(user => {
            console.log(user);
            this.originalUser = user;
            this.userForm.patchValue(user)
            this.isEditing = true;
          })
      };
    });
  }

  handleSubmit(e) {
    e.preventDefault();
    this.successSubmit = false;
    console.log('HELLO')
    if (this.userForm.valid) {
      this.isValid=true;
      if (this.isEditing) {
        let formData = this.userForm.value;
        let user = Object.assign({}, this.originalUser, formData);
        formData.email = user.email;
        this.userService.update(user.userId, user)
          .subscribe(question => {
            this.userForm.reset();
            this.router.navigateByUrl('home', { skipLocationChange: true })
              .then(() => this.router.navigate(['/home/user/view']));
            this.isEditing = false;
          })
        return;
      }
      else {
        this.userService.addUser(this.userForm.value).subscribe(item => {
          console.log(item);
          this.successSubmit = true;
          this.userForm.reset();
          
        });
      }
      
    }else{
      // let formValue = this.userForm.value;
      // if(!formValue.firstName || !formValue.lastName){
      //   this.errors['name']='Enter Complete Name';
      // }
      // if(!formValue.roundNumber){
      //   this.errors['round']='Round Cannot Be Empty';
      // }
      this.isValid = false;
    }
  }
}
