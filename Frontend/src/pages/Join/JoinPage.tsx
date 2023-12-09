import logoImg from 'assets/images/logo.png'
import { CommonHeader } from 'components/CommonHeader'
import { QuestionnaireModal } from 'components/QuestionnaireModal'
import { defaultDevelopmentStack } from 'constants/project/developmentStack'
import { useModal } from 'hooks/useModal'
import { FC, useState } from 'react'
import { DevelopmentStackType } from 'types/project'
import { translateDevelopmentStack } from 'utils/translateDevelopmentStack'
import {
  Container,
  ContentInput,
  ContentSelect,
  ContentTextArea,
  InputContainer,
  JoinButton,
  LogoImg,
  LogoTypo,
  QuestionnaireButton,
  QuestionnaireScoreTypo,
  Root,
} from './styled'
import { useNavigate } from 'react-router-dom'
import { PostUserJoinResponseType, postuserJoin } from 'api/postUserJoin'

type JoinPageProps = {
  className?: string
}

export const JoinPage: FC<JoinPageProps> = ({ className }) => {
  const navigate = useNavigate();
  
  const [id, setId] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [passwordCheck, setPasswordCheck] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [nickname, setNickname] = useState<string>("");
  const [introduce, setIntroduce] = useState<string>("");
  const [developmentStack, setDevelopmentStack] = useState<DevelopmentStackType>()
  const { open: questionnaireModalOpen, handleModal: handleQuestionnaireModal } = useModal({})
  const [questionnaireScore, setQuestionnaireScore] = useState<number>()



  const onClickJoin = () => {
    // api 넣기
    if(id.length > 0 && password.length > 0 && password === passwordCheck && email.length > 0 && nickname.length > 0 && introduce.length > 0) {
      // eslint-disable-next-line no-unused-vars
      let data = {
        id: id,
        password: password,
        nickname: nickname,
        introduce: introduce,
        email: email,
        developmentStack : developmentStack,
        scorePercent : questionnaireScore,
      }
      
      // userJoin 함수 호출하기
      postuserJoin(`${process.env.REACT_APP_BACKEND_URL}/user/join`, data)
    .then((response: PostUserJoinResponseType) => {
      if (response.status === 'SUCCESS') {
        // eslint-disable-next-line no-undef
        console.log('SUCCESS');
        navigate(`${process.env.REACT_APP_BACKEND_URL}/user/login`)
      } else {
        // eslint-disable-next-line no-undef
        alert("회원가입에 실패했습니다.")
        // eslint-disable-next-line no-undef
        console.log('Error message:', response.message);
      }
    })
    .catch((error: any) => {
      // eslint-disable-next-line no-undef
      console.error('Error :', error);
    });
    
      alert("회원가입을 완료했습니다.")
      navigate('/login');
    } else {
      // eslint-disable-next-line no-undef
      alert("회원가입에 실패했습니다.")
    }
    // 성공 시
    
  }

  return (
    <Root className={className}>
      <CommonHeader />
      <Container>
        <LogoImg src={logoImg} alt={'로고 이미지'} />
        <LogoTypo>당신의 능력, 티밍에서 펼쳐보세요!</LogoTypo>
        <InputContainer>
          <ContentInput placeholder="아이디" onChange={(e) => setId(e.target.value)}/>
          <ContentInput type="password" placeholder="비밀번호" onChange={(e) => setPassword(e.target.value)}/>
          <ContentInput type="password" placeholder="비밀번호 확인" onChange={(e) => setPasswordCheck(e.target.value)}/>
          <ContentInput placeholder="이메일" onChange={(e) => setEmail(e.target.value)}/>
          <ContentInput placeholder="닉네임" onChange={(e) => setNickname(e.target.value)}/>
          <ContentTextArea placeholder="자기소개" onChange={(e) => setIntroduce(e.target.value)}></ContentTextArea>
        </InputContainer>
        <JoinButton type={'primary'} onClick={onClickJoin}>회원가입</JoinButton>
      </Container>
    </Root>
  )
}
