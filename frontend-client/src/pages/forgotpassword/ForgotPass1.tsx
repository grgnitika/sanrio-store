// import React, { useState } from 'react';
// import axios from 'axios';
// import { useMutation } from '@tanstack/react-query';
// import { FaQuestionCircle, FaRegWindowClose } from 'react-icons/fa';
// import { RiLockPasswordFill } from 'react-icons/ri';
// import { IonIcon } from '@ionic/react';
// import { mailOutline } from 'ionicons/icons';
//
// const ForgotPass1: React.FC = () => {
//     const [email, setEmail] = useState<string>('');
//     const [securityQuestion, setSecurityQuestion] = useState<string>('');
//     const [newPassword, setNewPassword] = useState<string>('');
//     const [confirmPassword, setConfirmPassword] = useState<string>('');
//     const [error, setError] = useState<string>('');
//
//     // Use React Query for handling API mutations
//     const useApiCall = useMutation({
//         mutationKey: ['POST_RESET_PASSWORD'],
//         mutationFn: async () => {
//             try {
//                 // Validation: Check if any field is empty
//                 if (!email || !securityQuestion || !newPassword || !confirmPassword) {
//                     throw new Error('Please fill in all fields');
//                 }
//
//                 // Check if new password and confirm password match
//                 if (newPassword !== confirmPassword) {
//                     throw new Error('New password and confirm password do not match');
//                 }
//
//                 // Make the API call to reset the password
//                 const response = await axios.post('http://localhost:8080/register/resetPassword', {
//                     email,
//                     securityQuestion,
//                     password: newPassword,
//                     confirmPassword,
//                 });
//                 console.log('Password reset successfully:', response.data);
//
//                 // Redirect to another page on successful password reset
//                 window.location.href = '/';
//
//             } catch (error) {
//                 if (axios.isAxiosError(error)) {
//                     const errorMessage = error.response?.data?.message || 'Invalid email or security question';
//                     console.error('Failed to reset password:', errorMessage);
//                     setError(errorMessage);
//                 } else {
//                     const errorMessage = error.message || 'Unknown error';
//                     console.error('Failed to reset password:', errorMessage);
//                     setError(errorMessage);
//                 }
//
//             }
//         },
//     });
//
//     const handleResetPassword = () => {
//         // Clear previous error messages
//         setError('');
//
//         // Trigger the API call only if all fields are filled
//         useApiCall.mutate();
//     };
//
//     return (
//                 <div className={'wrapper1'}>
//                     <div className={'heading'}>
//                         <div className={"head11"}>FORGOT PASSWORD</div>
//                         <div className={"subhead11"}>PLEASE ENTER YOUR INFO TO VALIDATE</div>
//                     </div>
//                     <div className={'close-button'}>
//                         <button className="close-btn">
//                             <FaRegWindowClose />
//                         </button>
//                     </div>
//                     <div className={'input-section'}>
//                         <input
//                             className={'username_input'}
//                             type={'text'}
//                             placeholder={'Email'}
//                             value={email}
//                             onChange={(e) => setEmail(e.target.value)}
//                         />
//                         <span className={'mail-icon'}>
//                             <IonIcon icon={mailOutline} />
//                         </span>
//                     </div>
//                     <div className={'input-section'}>
//                         <input
//                             className={'question_input'}
//                             type={'text'}
//                             placeholder={'SECURITY QUESTION HERE'}
//                             value={securityQuestion}
//                             onChange={(e) => setSecurityQuestion(e.target.value)}
//                         />
//                         <span className={'mail-icon'}>
//                             <FaQuestionCircle />
//                         </span>
//                     </div>
//                     <div>
//                         <input
//                             className={'password_input'}
//                             type={'password'}
//                             placeholder={'NEW PASSWORD'}
//                             value={newPassword}
//                             onChange={(e) => setNewPassword(e.target.value)}
//                         />
//                         <span className={'iconpassword'}>
//                             <RiLockPasswordFill />
//                         </span>
//                     </div>
//                     <div>
//                         <input
//                             className={'confirm_input'}
//                             type={'password'}
//                             placeholder={'CONFIRM PASSWORD'}
//                             value={confirmPassword}
//                             onChange={(e) => setConfirmPassword(e.target.value)}
//                         />
//                         <span className={'iconpassword'}>
//                             <RiLockPasswordFill />
//                         </span>
//                     </div>
//                     <div className={'error-message'}>{error && <p>{error}</p>}</div>
//                     <div className={'send-button'}>
//                         <button className={'sendbtn'} onClick={handleResetPassword}>
//                             Reset
//                         </button>
//                     </div>
//                 </div>
//     );
// };
//
// export default ForgotPass1;
