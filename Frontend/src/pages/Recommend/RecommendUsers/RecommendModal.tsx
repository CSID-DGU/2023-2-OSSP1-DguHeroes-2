import React, { useState } from 'react';
import { Modal, Select, Form } from 'antd';
import { stacks } from 'types/stacks';

const { Option } = Select;

interface RecommendModalProps {
  visible: boolean;
  selectedItems: string[];
  onCancel: () => void;
  onHandlePositionStack: (selectedValues: string[]) => void;
}

const RecommendModal: React.FC<RecommendModalProps> = ({
                                                         visible,
                                                         selectedItems,
                                                         onCancel,
                                                         onHandlePositionStack,
                                                       }) => {
  const [selectedTargets, setSelectedTargets] = useState<string[]>([]);

  const handleSelectChange = (values: string[]) => {
    setSelectedTargets(values);
  };

  const handleOk = () => {
    onHandlePositionStack(selectedTargets);
    onCancel();
    setSelectedTargets([]); // Ok 버튼 눌렀을 때 초기화
  };

  const handleCancel = () => {
    onCancel();
    setSelectedTargets([]); // Cancel 버튼이나 X 버튼 눌렀을 때 초기화
  };

  return (
    <Modal
      title="추천 팀원 보기"
      visible={visible}
      onCancel={handleCancel}
      onOk={handleOk}
      width={800}
      bodyStyle={{ height: '800px', overflowY: 'auto' }}
    >
      <Form.Item>
        <Select
          mode="multiple"
          placeholder="추천 팀원"
          onChange={handleSelectChange}
          value={selectedTargets}
          style={{ width: '100%' }}
        >
          {selectedItems.map((selectItem) => {
            const stackItem = stacks.find((stack) => stack.value === selectItem);
            return (
              <Option key={stackItem?.value} value={stackItem?.label}>
                {stackItem?.label}
              </Option>
            );
          })}
        </Select>
      </Form.Item>
    </Modal>
  );
};

export default RecommendModal;
