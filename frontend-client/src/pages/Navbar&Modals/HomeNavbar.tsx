import logo from "../../images/sanriostore_logo.png";
import {Link, useNavigate} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {FaCartArrowDown, FaQuestionCircle, FaRegWindowClose, FaUser} from "react-icons/fa";
import {RiLockPasswordFill} from "react-icons/ri";
import gsap from "gsap";
import "../../css/LoginPage.css"
import "../../css/RegistrationPage.css"
import "../../css/HomeNavbar.css"
import '../../css/ForgotPass1.css';
import {useForm} from "react-hook-form";
import {useMutation} from "@tanstack/react-query";
import axios from "axios";
import {toast, ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {FaCircleUser} from "react-icons/fa6";
import UserProfileView from "../UserProfileView.tsx";
import {MdEmail} from "react-icons/md";
import {useQuery} from "@tanstack/react-query";


interface HomeNavbarProps {
    activePage: string;
}

interface LoginProps {
    onLoginSuccess: () => void;
}


const HomeNavbar: React.FC<HomeNavbarProps> = ({ activePage }) => {

    const [loginSuccess, setLoginSuccess] = useState(false);
    const [loginError, setLoginError] = useState<string>('');

    const navigate = useNavigate();


    // Fetching cartdata from API
    const{data:cartData} = useQuery({
        queryKey:["GET_CART_DATA"],
        queryFn(){
            return axios.get("http://localhost:8080/cart/getAll")
        }
    })


    // Login modal
    const [login_popup, setLModal] = useState(false);
    const toggleLoginModal = () => {
        setLModal(!login_popup);
        setRPopup(false); // Close the registration modal
    };

    // Registration modal
    const [register_popup, setRPopup] = useState(false);
    const toggleRegisterModal = () => {
        setRPopup(!register_popup);
        setLModal(false); // Close the login modal
    };
    //forget password backend
    const [email, setEmail] = useState<string>('');
    const [securityQuestion, setSecurityQuestion] = useState<string>('');
    const [newPassword, setNewPassword] = useState<string>('');
    const [confirmPassword, setConfirmPassword] = useState<string>('');
    const [error, setError] = useState<string>('');
    //forget popup
    const [forget_popup, setfPopup] = useState(false);
    const toggleforgetModal = () => {
        setfPopup(!forget_popup);
        setLModal(false); // Close the login modal
    };

    // Use React Query for handling API mutations
    const useApiforgetCall = useMutation({
        mutationKey: ['POST_RESET_PASSWORD'],
        mutationFn: async () => {
            try {
                // Validation: Check if any field is empty
                if (!email || !securityQuestion || !newPassword || !confirmPassword) {
                    throw new Error('Please fill in all fields');
                }

                // Check if new password and confirm password match
                if (newPassword !== confirmPassword) {
                    throw new Error('New password and confirm password do not match');
                }

                // Make the API call to reset the password
                const response = await axios.post('http://localhost:8080/register/resetPassword', {
                    email,
                    securityQuestion,
                    password: newPassword,
                    confirmPassword,
                });
                console.log('Password reset successfully:', response.data);

                // Redirect to another page on successful password reset
                window.location.href = '/';

            } catch (error) {
                if (axios.isAxiosError(error)) {
                    const errorMessage = error.response?.data?.message || 'Invalid email or security question';
                    console.error('Failed to reset password:', errorMessage);
                    setError(errorMessage);
                } else {
                    const errorMessage = error.message || 'Unknown error';
                    console.error('Failed to reset password:', errorMessage);
                    setError(errorMessage);
                }

            }
        },
    });
    const handleResetPassword = () => {
        // Clear previous error messages
        setError('');

        // Trigger the API call only if all fields are filled
        useApiforgetCall.mutate();
    };



    if (login_popup || register_popup) {
        document.body.classList.add('active-modal');
    } else {
        document.body.classList.remove('active-modal');
    }

    useEffect(() => {
        const storedData = localStorage.getItem("userDetails");
        if (storedData) {
            setUser(JSON.parse(storedData));

            console.log(user)
        }
    }, []);

    useEffect(() => {
        const modalAnimation = (modalClass: string | null) => {
            if (modalClass) {
                gsap.from(`.${modalClass}`, {
                    duration: 0.3,
                    opacity: 0,
                    onComplete: () => {
                        // Callback when the animation is complete
                        if (login_popup) {
                            document.body.classList.add('active-login-modal');
                        } else if (register_popup) {
                            document.body.classList.add('active-register-modal');
                        }
                    },
                });
            }
        };
        modalAnimation(login_popup ? "login-modal" : (register_popup ? "register-modal" : null));

    }, [login_popup, register_popup]);

    const [userProfile , setUserProfile] = useState(false);

    //Register ko backend connection
    const {
        register,
        handleSubmit,
        reset,
        formState: { errors },
        watch,
    } = useForm();

    // const {errors}=formState;

    const useApiCall=useMutation({
        mutationKey:["POST_USER_CREATE"],
        mutationFn:(payload:any)=>{
            console.log(payload)
            return axios.post("http://localhost:8080/register/register",payload)
        },
        onSuccess: () => {
            difftoast();
            reset();
        },
    })

    const [user, setUser] = useState(null);

    // console.log("User",user.fullName)

    const useApiCallLogin = useMutation({
        mutationKey: ["POST_USER_LOGIN"],
        mutationFn: (payload: any) => {
            console.log(payload);
            return axios.post("http://localhost:8080/register/login", payload);
        },
        onSuccess: (response) => {
            const userData = response.data;
            if (userData) {
                console.log("User Data:", userData);

                try {
                    localStorage.setItem("userDetails", JSON.stringify(userData));

                    handleLoginSuccess();
                    const data: any = JSON.parse(localStorage.getItem("userDetails"));
                    console.log(data);
                    console.log(typeof data);
                    setUser(data);
                    reset();

                    if (userData.roles === "ADMIN") {
                        // Redirect to admin page or perform admin-related actions
                        navigate('/AdminDashboard'); // Assuming you have a route for the admin page
                    } else {
                        console.error("User details not found in the response");
                    }
                } catch (error) {
                    if (axios.isAxiosError(error)) {
                        const errorMessage = error.response?.data?.message || 'Invalid email or password';
                        console.error('Failed to login:', errorMessage);
                        setLoginError((prevError) => {
                            console.log('Previous Error:', prevError); // Debug statement
                            console.log('New Error:', errorMessage); // Debug statement
                            return errorMessage;
                        });
                    }
                }
            }
        },
    });


    const handleLoginSuccess = () => {
        setLoginSuccess(true);
    };


    useEffect(() => {
        // Close login popup when login success state is true
        if (loginSuccess) {
            toggleLoginModal();
            setLoginSuccess(false);  // Reset login success state
        }
    }, [loginSuccess, toggleLoginModal]);

    const onSubmit=(value:any)=>{
        useApiCall.mutate(value)
    }

    const onSubmitLogin = async (values: any) => {
        try {
            const response = await useApiCallLogin.mutate(values);
            console.log('Login API Response:', response);

            // Check if the response has data indicating a successful login
            if (response.data) {
                // Handle successful login (if needed)
                handleLoginSuccess();
            } else {
                // If no data is received, consider it an unsuccessful login
                throw new Error('Invalid email or password');
            }
        } catch (error) {
            console.error('Login Error:', error);

            // Check for specific error cases, such as 401 Unauthorized
            if (axios.isAxiosError(error) && error.response?.status === 401) {
                setLoginError('Invalid email or password');
            } else {
                // Handle other error cases
                setLoginError('Invalid email or password');
            }
            setTimeout(() => {
                setLoginError('');
            }, 2000);
        }
    };

    const   handleLogout = () => {
        // Check if a user is currently logged in
        const isLoggedIn = Boolean(localStorage.getItem('userDetails'));

        if (isLoggedIn) {
            // Clear user details from localStorage
            localStorage.removeItem('userDetails');

            // Update the state to reflect logout
            setUser(null);

            navigate('/');
        }
    };

    const difftoast =() => {
        toast.success("wow! you just register", {position: "top-center"})
    }
    // const difftoast1 =() => {
    //     toast.success("inavalid", {position: "top-center"})
    // }

    return(
        <>
            <div className={"nav-bar"}>
                <div className={"feast-logo"}>
                    <img src={logo} width={"90px"} />
                </div>
                <div className={"home-options"}>
                    <ul>
                        <Link to={"/"}><li className={activePage === "/" ? "active" : ""}><a>Home</a></li></Link>
                        <Link to={"/OurMenu"}><li className={activePage === "/OurMenu" ? "active" : ""}><a>Our Products</a></li></Link>
                        {/*<Link to={"/ReservationPage"}><li className={activePage === "/ReservationPage" ? "active" : ""}><a>ReservationPage</a></li></Link>*/}
                        {/*<Link to={"/EventsPage"}><li className={activePage === "/EventsPage" ? "active" : ""}><a>Events</a></li></Link>*/}
                        {/**/}
                    </ul>
                </div>

                <div className={"hp-navright"}>
                    {user ? (
                        <Link to={"/cart"}>
                            <span className={"icon-cart"}>
                                <FaCartArrowDown style={{ fontSize: "1.2rem", marginBottom: "-6px", marginRight: "10px" }} />
                                <h6 className={"icon-cart-number"}>{cartData?.data.length}</h6>
                            </span>
                        </Link>
                    ) : (
                        <span className={"unclickable-cart-icon"} onClick={() => alert("Please sign in to access the cart.")}>
                            <span className={"icon-cart"}>
                                <FaCartArrowDown style={{ fontSize: "1.2rem", marginBottom: "-6px", marginRight: "10px", color: "gray" }} />
                            </span>
                        </span>
                    )}
                    {user ? (
                             <span className={"fullnamedisplay"} onClick={() => setUserProfile(true)}>
                                <FaCircleUser style={{ fontSize: "3rem", marginBottom: "-3px", marginRight: "3px" }} />
                             </span>
                    ):
                        (
                        !localStorage.getItem("userDetails") && (
                            <div className={"hp-sign-btn"}>
                                <h3 onClick={toggleLoginModal}>Sign in/Login</h3>
                            </div>
                        )
                    )}
                </div>
            </div>

            {login_popup && (
                <div className="login-modal">
                    <div onClick={toggleLoginModal} className="login-overlay"></div>
                    <div className="login-modal-content">
                        <h2>Login</h2>
                        <button className="close-login-btn" onClick={toggleLoginModal}>
                            <FaRegWindowClose />
                        </button>
                        <form onSubmit={handleSubmit(onSubmitLogin)}>
                            <div className={"input-box"}>
                                <span className={"iconmail"}> <FaUser /></span>
                                <div className={"username"}>
                                    <input
                                        type={"email"}
                                        placeholder={"Email"}
                                        {...register("email")}
                                    />
                                </div>
                                <span className={"iconpassword"}><RiLockPasswordFill/></span>
                                <div className={"password"}>
                                    <input
                                        type={"password"}
                                        placeholder={"Password"}
                                        {...register("password")}
                                    />
                                </div>
                            </div>
                            <div className={"Remember-forget"}>
                                <label><input type={"checkbox"}/>Remember me</label>
                                <a href={"#"} onClick={toggleforgetModal}>Forget passsword?</a>
                            </div>
                            <div className={'error-message top-error-message'}>
                                {loginError && <p>{loginError}</p>}
                            </div>
                            <button type={"submit"} className={"btn-login10"} >Login</button>

                            <div className={"register-text"}>
                                <p> Don't have an account?
                                    <a href={"#"} onClick={toggleRegisterModal}>Register</a></p>
                            </div>
                        </form>
                    </div>
                </div>
            )}

            {register_popup && (
                <div className="register-modal">
                    <div onClick={toggleRegisterModal} className="register-overlay"></div>
                    <div className="register-modal-content">
                        <form onSubmit={handleSubmit(onSubmit)}>
                            <h2>Register</h2>
                            <button className="close-register-btn" onClick={toggleRegisterModal}><FaRegWindowClose /></button>
                            <div className={"reg-input-box"}>
                                <div className={"username"}>
                                    <input type={"text"} placeholder={"Name"} {...register("fullName",{
                                        required:"FullName is required!!"
                                    })}
                                    />
                                    {errors.fullName && (
                                        <p className="error-message">{errors?.fullName?.message}
                                        </p>
                                    )}
                                </div>
                                <div className={"username"}>
                                    <input type={"email"} placeholder={"Email"}  {...register("email",
                                        {required:"Email is required!!"})}/>
                                    {errors.email && (
                                        <p className="error-message">{errors?.email?.message}
                                        </p>
                                    )}
                                </div>
                                {/*<span className={"iconuser"}><FaUser /> </span>*/}
                                {/*<span className={"iconpassword"}><RiLockPasswordFill /></span>*/}
                                <div className={"password"}>
                                    <input
                                        type={"password"}
                                        placeholder={"Password"}
                                        {...register("password", {
                                            required: "Password is required!!",
                                            minLength: {
                                                value: 6,
                                                message: "Password should be at least 6 characters long",
                                            },
                                        })}
                                    />
                                    {errors.password && (
                                        <p className="error-message">{errors?.password?.message}</p>
                                    )}
                                </div>
                                <div className={"password"}>

                                    <input type={"password"} placeholder={"Confirm Password"}
                                           {...register("confirmPassword", {


                                               required: "Confirm Password is required",
                                               validate: {
                                                   matchesPassword: (value) =>
                                                       value === watch("password") || "Confirm Password does not match Password",
                                               },
                                           })}
                                    />

                                    {errors.confirmPassword && (
                                        <p className="error-message">{errors?.confirmPassword?.message}
                                        </p>
                                    )}
                                </div>
                            </div>
                            <div className={"security-question"}>
                                <div className={"header10"}>Security Question</div>
                                <div className={"answer"}>
                                    <input type={"answer"} placeholder={"Your first school name?"}  {...register("securityQuestion",
                                        {required:"SecurityQuestion is required!!"})}/>
                                    {errors.securityQuestion && (
                                        <p className="error-message">{errors?.securityQuestion?.message}
                                        </p>
                                    )}
                                </div>

                            </div>
                            <button type={"submit"} className={"btn-signup10"}>Sign Up</button>
                            <ToastContainer/>
                        </form>
                    </div>
                </div>
            )}

            {forget_popup &&(
                <div  className={"forget-modal"}>
                    <div onClick={toggleforgetModal} className={"forget-overlay"}></div>
                    <div className={"forget-modal-content"}>
                        <div className={'heading'}>
                            <h2>FORGOT PASSWORD?</h2>
                            <h3>Please fill your crediantials</h3>
                        </div>
                        <div className={'close-button'}>
                            <button className="close-btn" onClick={toggleforgetModal}>
                                <FaRegWindowClose />
                            </button>
                        </div>
                        <div className={'input-section'}>
                            <input
                                className={'username_input'}
                                type={'text'}
                                placeholder={'Email'}
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                            />
                            <span className={'mail-icon'}>
                            <MdEmail />
                        </span>
                        </div>
                        <div className={'input-section'}>
                            <input
                                className={'question_input'}
                                type={'text'}
                                placeholder={'Security question here'}
                                value={securityQuestion}
                                onChange={(e) => setSecurityQuestion(e.target.value)}
                            />
                            <span className={'security-question-icon'}>
                            <FaQuestionCircle/>
                        </span>
                        </div>
                        <div className={"input-section"}>
                            <input
                                className={'password_input'}
                                type={'password'}
                                placeholder={'New password'}
                                value={newPassword}
                                onChange={(e) => setNewPassword(e.target.value)}
                            />
                            <span className={'iconpassword'}>
                            <RiLockPasswordFill />
                        </span>
                        </div>
                        <div className={"input-section"}>
                            <input
                                className={'confirm_input'}
                                type={'password'}
                                placeholder={'Confirm password'}
                                value={confirmPassword}
                                onChange={(e) => setConfirmPassword(e.target.value)}
                            />
                            <span className={'iconpassword'}>
                            <RiLockPasswordFill />
                            </span>
                        </div>
                        <div className={'error-message'}>{error && <p>{error}</p>}</div>
                        <div className={'send-button'}>
                            <button className={'sendbtn'} onClick={handleResetPassword}>
                                Reset
                            </button>
                        </div>
                    </div>
                </div>
            )}

            {userProfile && <UserProfileView />}

        </>
    )
}

export default HomeNavbar