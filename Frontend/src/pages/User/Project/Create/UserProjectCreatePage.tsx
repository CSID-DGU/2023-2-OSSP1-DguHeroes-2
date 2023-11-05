/* eslint-disable */
import projectTitleIconImg from 'assets/images/project/titleIcon.png'
import { CommonHeader } from 'components/CommonHeader'
import { FC, useState } from 'react'
import {
  Container,
  ProjectOptionContainer,
  Root,
  TitleContainer,
  TitleLogoImg,
  TitleTypo,
  InputTitle,
  ProjectOptionLeftContainer,
  ProjectOptionRightContainer,
  InputContainer,
  LeaderPositionContainer,
  InputTitleRequired,
  SearchContainer,
  ProjectMemberInputTitleContainer,
  ProjectMemberExplainIcon,
  ProjectMemberExplainWrapper,
  ProjectMemberExplainContentWrapper,
  ProjectMemberExplainTitle,
  ProjectMemberExplainText,
  ProjectMemberInputContainer,
  ProjectDateContainer,
  ProjectCreateButton,
  LocationContainer,
  InputTitleContainer,
} from './styled'
// antd 적용하기
import { Form, Input, Select, DatePicker, Row, Col, Slider, Radio, RadioChangeEvent } from 'antd'
import { CreateProjectSection } from './CreateProjectSection'
import type { SliderMarks } from 'antd/es/slider';
import { locationOptions } from 'constants/project/locationOptions'
import { DevelopmentStackType, ProjectRequireMemberListType, ProjectType } from 'types/project'
import { PostProjectCreateResponseType, postprojectCreate } from 'api/postProjectCreate'
import { useNavigate } from 'react-router-dom'

type UserProjectCreatePageProps = {
  className?: string
}

const { Option } = Select

interface StackType {
  id: number;
  label: string;
  key: string;
}

const stackName: StackType[] = [
  {id: 0, label: '프론트엔드', key: 'WEB_FRONTEND'},
  {id: 1, label: '백엔드', key: 'SERVER_BACKEND'},
  {id: 2, label: '앱 클라이언트', key: 'APP_CLIENT'},
  {id: 3, label: '기타', key: 'ETC'},
];

const marks: SliderMarks = {
  0: {
    style: {
      fontSize: '30px',
    },
    label: '🥚',
  },
  25: {
    style: {
      fontSize: '30px',
    },
    label: '🐣',
  },
  50: {
    style: {
      fontSize: '30px',
    },
    label: '🐥',
  },
  75: {
    style: {
      fontSize: '30px',
    },
    label: '🐔',
  },
  100: {
    style: {
      fontSize: '30px',
    },
    label: '🦢',
  },
};

const content = (
  <ProjectMemberExplainContentWrapper>
    <ProjectMemberExplainTitle>원하는 팀원의 실력을 정해주세요.</ProjectMemberExplainTitle>
    <ProjectMemberExplainText>다음은 회원가입 시 푸는 퀴즈에 따라 분류된 등급입니다.</ProjectMemberExplainText>
    <ProjectMemberExplainText>E는 가장 낮은 등급, A는 가장 높은 등급입니다.</ProjectMemberExplainText>
    <Slider marks={marks} defaultValue={100} disabled={true}/>
  </ProjectMemberExplainContentWrapper>
);

const { RangePicker } = DatePicker;

const dateFormat = 'YYYY/MM/DD';

export const UserProjectCreatePage: FC<UserProjectCreatePageProps> = ({ className }) => {
  const navigate = useNavigate()

  const [title, setTitle] = useState<string>("");
  const [projectType, setProjectType] = useState<ProjectType>();
  const [requireMemberList, setRequireMemberList] = useState<ProjectRequireMemberListType>([
    {
      developmentStack: "WEB_FRONTEND",
      recommendScore: undefined,
      number: undefined
    },
    {
      developmentStack: "SERVER_BACKEND",
      recommendScore: undefined,
      number: undefined
    },
    {
      developmentStack: "APP_CLIENT",
      recommendScore: undefined,
      number: undefined
    },
    {
      developmentStack: "ETC",
      recommendScore: undefined,
      number: undefined
    }]);
  const [leaderDevelopmentStack, setLeaderDevelopmentStack] = useState<DevelopmentStackType>()
  const [location, setLocation] = useState<number>()
  const [projectStartDate, setProjectStartDate] = useState<string>()
  const [projectEndDate, setProjectEndDate] = useState<string>()
  const [projectContent, setProjectContent] = useState<string>("")

  const onClickProjectCreate = () => {
    if(title.length > 0 && projectType !== undefined && leaderDevelopmentStack !== undefined && location !== undefined && projectStartDate !== undefined && projectEndDate !== undefined && projectContent.length > 0) {
      
    const filteredrequireMemberList = requireMemberList.filter((member) => member.number !== undefined && member.number > 0 && member.recommendScore !== undefined && member.recommendScore > 0)
      if(filteredrequireMemberList.length === 0) {
        // eslint-disable-next-line no-undef
        alert("입력값을 모두 채워주세요.")
        return
      }
      const data = {
        title: title,
        projectType: projectType,
        requireMemberList: filteredrequireMemberList,
        leaderDevelopmentStack: leaderDevelopmentStack,
        location: location,
        projectStartDate: projectStartDate,
        projectEndDate: projectEndDate,
        projectContent: projectContent
      }
      // api 호출하기
      postprojectCreate('/project/create', data)
      .then((response: PostProjectCreateResponseType) => {
        if (response.status === 'SUCCESS') {
          // eslint-disable-next-line no-undef
          console.log('SUCCESS');
          navigate('/')
        } else {
          // eslint-disable-next-line no-undef
          console.log('FAIL');
          // eslint-disable-next-line no-undef
          console.log('Error message:', response.message);
        }
      })
      .catch((error: any) => {
        // eslint-disable-next-line no-undef
        console.error('Error :', error);
      });
    } else {
      // eslint-disable-next-line no-undef
    alert("프로젝트가 생성되었습니다.")
    navigate('/')
    }
  }

  const onChangeProjectType = (e: RadioChangeEvent) => {
    setProjectType(e.target.value);
  }

  const onChangeProjectMemberNumber = (e: any, key: string) => {
    setRequireMemberList(requireMemberList.map((member) => member.developmentStack === key ? { ...member, number: parseInt(e.target.value) } : member ));
  }

  const onChangeProjectMemberScore = (value: string, key: string) => {
    setRequireMemberList(requireMemberList.map((member) => member.developmentStack === key ? { ...member, recommendScore: parseInt(value) } : member ));
  }

  const onProjectDateChange = (dates: any, dateStrings: string[]) => {
    if (dates) {
      setProjectStartDate(dateStrings[0])
      setProjectEndDate(dateStrings[1])
    } else {
      setProjectStartDate(undefined)
      setProjectEndDate(undefined)
    }
  };

  


  return (
    <Root className={className}>
      <CommonHeader />
      <Container>
        <TitleContainer>
          <TitleLogoImg src={projectTitleIconImg} alt={'요즘 뜨는 프로젝트 로고 이미지'} />
          <TitleTypo>프로젝트 생성하기</TitleTypo>
        </TitleContainer>
        <ProjectOptionContainer>
          <ProjectOptionLeftContainer>
            <Form layout="vertical" autoComplete="off">
              <Form.Item style={{ marginBottom: 0}}>
                <ProjectMemberInputTitleContainer>
                  <InputTitleRequired>모집인원</InputTitleRequired>
                  <ProjectMemberExplainWrapper content={content} title="등급 안내" placement="right">
                    <ProjectMemberExplainIcon />
                  </ProjectMemberExplainWrapper>
                </ProjectMemberInputTitleContainer>
                {stackName
                  .map((stackItem) => (
                    <ProjectMemberInputContainer key={stackItem.id}>
                      <Form.Item
                        name="memberStack"
                        style={{ display: 'inline-block', width: 'calc(40% - 8px)', marginBottom: '5px'}}
                      >
                        <div>{stackItem.label}</div>
                      </Form.Item>
                      <Form.Item
                        name={`number_${stackItem.key}`}
                        style={{ display: 'inline-block', width: 'calc(30% - 8px)', marginLeft: '5px', marginBottom: 0 }}
                      >
                        <Input onChange={(e) => onChangeProjectMemberNumber(e, stackItem.key)} type='number' placeholder="인원" />
                      </Form.Item>
                      <Form.Item
                        name={`grade_${stackItem.key}`}
                        style={{ display: 'inline-block', width: 'calc(30% - 8px)', marginLeft: '5px', marginBottom: 0  }}
                      >
                        <Select placeholder="등급" onChange={(value) => onChangeProjectMemberScore(value, stackItem.key)}>
                          <Option value='1'>A</Option>
                          <Option value='2'>B</Option>
                          <Option value='3'>C</Option>
                          <Option value='4'>D</Option>
                          <Option value='5'>E</Option>
                        </Select>
                      </Form.Item>
                  </ProjectMemberInputContainer>
                  ))
                }
              </Form.Item>
            </Form>
          </ProjectOptionLeftContainer>
          <ProjectOptionRightContainer>
            <Form layout="vertical" autoComplete="off">
              <InputTitleContainer>
                <InputTitleRequired>프로젝트 제목</InputTitleRequired>
                <Input onClick={(e) => setTitle(e.currentTarget.value)} placeholder='프로젝트 보고서'/>
              </InputTitleContainer>
              <InputContainer>
                <LeaderPositionContainer>
                  <InputTitleRequired>팀장 포지션</InputTitleRequired>
                  <Form.Item name="leader-position">
                    <Select placeholder="팀장 포지션" onChange={(value) => setLeaderDevelopmentStack(value)}>
                      <Option value="WEB_FRONTEND">프론트</Option>
                      <Option value="SERVER_BACKEND">백엔드</Option>
                      <Option value="APP_CLIENT">앱 클라이언트</Option>
                      <Option value="ETC">기타</Option>
                    </Select>
                  </Form.Item>
                </LeaderPositionContainer>
                <ProjectDateContainer>
                  <InputTitleRequired>프로젝트 기간</InputTitleRequired>
                  <InputContainer>
                    <RangePicker format={dateFormat} onChange={onProjectDateChange}/>
                  </InputContainer>
                </ProjectDateContainer>
              </InputContainer>
              <Form.Item>
                <InputTitleRequired>분야</InputTitleRequired>
                <Radio.Group onChange={onChangeProjectType}>
                  <Row style={{ flexFlow: 'row nowrap' }}>
                    <Col span={10}>
                      <Radio value="WEB" style={{ lineHeight: '32px' }}>
                        WEB
                      </Radio>
                    </Col>
                    <Col span={10}>
                      <Radio value="SERVER" style={{ lineHeight: '32px' }}>
                        SERVER
                      </Radio>
                    </Col>
                    <Col span={10}>
                      <Radio value="APP" style={{ lineHeight: '32px' }}>
                        APP
                      </Radio>
                    </Col>
                    <Col span={10}>
                      <Radio value="ETC" style={{ lineHeight: '32px' }}>
                        ETC
                      </Radio>
                    </Col>
                  </Row>
                </Radio.Group>
              </Form.Item>
            </Form>
          </ProjectOptionRightContainer>
        </ProjectOptionContainer>
        <SearchContainer>
          <LocationContainer>
            <InputTitle>지역</InputTitle>
            <Select onChange={(value) => setLocation(value)} defaultValue={0} options={locationOptions} size="large" placeholder="지역 선택" style={{ width: 200 }}/>
          </LocationContainer>
          <ProjectCreateButton type="primary" onClick={onClickProjectCreate}>프로젝트 생성하기</ProjectCreateButton>
        </SearchContainer>
        <CreateProjectSection setProjectContent={setProjectContent}/>
      </Container>
    </Root>
  )
}
