/* eslint-disable  */
import { CommonHeader } from 'components/CommonHeader';
import { ProjectCard } from 'components/ProjectCard';
import projectListSampleJson from 'constants/json/project_list_sample.json';
import React, { FC, useEffect, useState } from 'react';
import { ProjectListType, ProjectType } from 'types/project';
import { camelizeKey } from 'utils/camelizeKey';
import { ProjectCardContainer, Root, Container, SelectContainer, SearchBox, SearchContainer, SelectBox, TitleTypo } from './styled';
import { locationOptions } from 'constants/project/locationOptions';
import { getmainInfo } from 'api/getMainInfo';
import { GetProjectListResponseType, getProjectList } from 'api/getProjectList';

type ProjectListPageProps = {
  className?: string;
};

const projectListData = camelizeKey(projectListSampleJson.project_list) as ProjectListType;

type OptionType = {
  value: string;
  label: string;
};

const projectTypeOptions: OptionType[] = [
  { value: "WEB", label: "WEB" },
  { value: "MOBILE APP", label: "MOBILE APP" },
  { value: "DESKTOP APP", label: "DESKTOP APP" },
  { value: "WEB APP", label: "WEB APP" },
  { value: "ETC", label: "ETC" },
];

const stackOptions: OptionType[] = [
  { value: "frontend", label: "프론트엔드" },
  { value: "backend", label: "백엔드" },
  { value: "etc", label: "기타" },
];

export const ProjectListPage: FC<ProjectListPageProps> = ({ className }) => {
  const [search, setSearch] = useState('')
  const [projectTypeSelect, setProjectTypeSelect] = useState('')
  const [stackTypeSelect, setStackTypeSelect] = useState('')
  const [locationTypeSelect, setLocationTypeSelect] = useState('')

  useEffect(() => {
    getProjectList()
    .then((response: GetProjectListResponseType) => {
      if (response.status === 'SUCCESS') {
        console.log('SUCCESS');
        // projectList 받아서 가공하기
      } else {
        console.log('FAIL');
        console.log('Error message:', response.message);
      }
    })
    .catch((error: any) => {
      console.error('Error :', error);
    });
  }, [])

  const filteredProjectListData = projectListData.filter(
    (projectItem) =>
      projectItem.title.toLowerCase().includes(search.toLowerCase()) &&
      projectItem.projectType.toLowerCase().includes(projectTypeSelect.toLowerCase()) &&
      projectItem.requireMemberList.some((member) => member.developmentStack.toLowerCase().includes(stackTypeSelect)) &&
      (locationTypeSelect === '' || projectItem.location === parseInt(locationTypeSelect))
  )

  const renderProjectList = () => {
    return (
      <ProjectCardContainer>
        {filteredProjectListData
          .sort((a, b) => a.key - b.key)
          .map((projectItem) => (
            <ProjectCard projectItem={projectItem} key={`project_card_${projectItem.key}`} />
          ))}
      </ProjectCardContainer>
    )
  }

  const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearch(event.currentTarget.value)
  }

  const handleProjectTypeSelectChange = (option: any) => {
    setProjectTypeSelect(option)
  }

  const handleStackTypeSelectChange = (option: any) => {
    setStackTypeSelect(option)
  }

  const handleLocationTypeSelectChange = (option: any) => {
    setLocationTypeSelect(option)
  }

  return (
    <Root className={className}>
      <CommonHeader />
      <Container>
        <SelectContainer>
          <TitleTypo>분야 검색</TitleTypo>
          <SelectBox onChange={(option) => handleProjectTypeSelectChange(option)} defaultValue="WEB" options={projectTypeOptions} />
        </SelectContainer>
        <SelectContainer>
          <TitleTypo>포지션 검색</TitleTypo>
          <SelectBox onChange={(option) => handleStackTypeSelectChange(option)} defaultValue="frontend" options={stackOptions} />
        </SelectContainer>
        <SelectContainer>
          <TitleTypo>지역 검색</TitleTypo>
          <SelectBox onChange={(option) => handleLocationTypeSelectChange(option)} defaultValue={0} options={locationOptions} />
        </SelectContainer>
      </Container>
      <Container>
        <SearchContainer>
          <TitleTypo>제목 검색</TitleTypo>
          <SearchBox
            type="search"
            id="psearch"
            name="psearch"
            maxLength={20}
            placeholder="제목 검색"
            onChange={handleSearchChange}
          />
        </SearchContainer>
      </Container>
      {renderProjectList()}
    </Root>
  )
}
