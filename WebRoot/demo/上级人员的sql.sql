 select * from t_bd_person p where p.fid='vIvZAsdBTJqDoWwVZxt/YoDvfe0=';
 ------------联查：查询出来人员所在的岗位
 select Pm.FPositionID,Po.Fname_L2 from t_bd_person P 
 inner join T_ORG_PositionMember Pm  on P.fid = Pm.FPersonID
  join T_ORG_Position Po  on Po.fid = Pm.FPositionID
  where P.fid = 'vIvZAsdBTJqDoWwVZxt/YoDvfe0=';
  ----上级的职位编码
  select Ph.Fparentid 
  from  T_ORG_Position Po
  join T_ORG_PositionHierarchy Ph on Ph.Fchildid = Po.Fid
   join t_Org_Admin A on A.Fid = Po.Fadminorgunitid
   join T_ORG_Position Po2 on Ph.Fparentid = Po2.fid
   where Po.Fid = '5appxvWFTRygxF8jext9aHSuYS4=';
 -----上级人员的信息
select p.fname_l2 from t_bd_person P 
 inner join T_ORG_PositionMember Pm  on P.fid = Pm.FPersonID
 join T_ORG_Position Po  on Po.fid = Pm.FPositionID
 where Po.Fid = 'Y1Qyv+dkSwSda0bYMpFiP3SuYS4=';
 -------------------联查一下试试----------
  select p.fname_l2 from t_bd_person P 
 inner join T_ORG_PositionMember Pm  on P.fid = Pm.FPersonID
 join T_ORG_Position Po  on Po.fid = Pm.FPositionID
 where Po.Fid = (
 	select Ph.Fparentid 
  from  T_ORG_Position Po
  join T_ORG_PositionHierarchy Ph on Ph.Fchildid = Po.Fid
   join t_Org_Admin A on A.Fid = Po.Fadminorgunitid
   join T_ORG_Position Po2 on Ph.Fparentid = Po2.fid
   where Po.Fid = '5appxvWFTRygxF8jext9aHSuYS4='
 )