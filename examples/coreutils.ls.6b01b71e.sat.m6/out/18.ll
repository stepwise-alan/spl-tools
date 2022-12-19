; ModuleID = '/tmp/sea-m8wn2zfu/18.pp.ms.o.bc'
source_filename = "18.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@llvm.used = appending global [16 x i8*] [i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*), i8* bitcast (void (i1)* @verifier.assume to i8*), i8* bitcast (void (i1)* @verifier.assume.not to i8*), i8* bitcast (void ()* @verifier.error to i8*), i8* bitcast (void ()* @seahorn.fail to i8*)], section "llvm.metadata"

declare i64 @_nd_st_size1() local_unnamed_addr #0

declare i64 @_nd_st_size2() local_unnamed_addr #0

declare i64 @_nd_st_atim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_atim_tv_sec2() local_unnamed_addr #0

declare i64 @_nd_st_ctim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_ctim_tv_sec2() local_unnamed_addr #0

declare i64 @_nd_st_mtim_tv_sec1() local_unnamed_addr #0

declare i64 @_nd_st_mtim_tv_sec2() local_unnamed_addr #0

declare i32 @_nd_st_mode1() local_unnamed_addr #0

declare i32 @_nd_st_mode2() local_unnamed_addr #0

declare i32 @_nd_strcoll12() local_unnamed_addr #0

declare i32 @_nd_strcmp12() local_unnamed_addr #0

declare i32 @_nd_all_fmt() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_size() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_atime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_ctime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_mtime() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_dir() local_unnamed_addr #0

declare zeroext i1 @_nd_sort_reverse() local_unnamed_addr #0

declare zeroext i1 @_nd_CONFIG_LOCALE_SUPPORT() local_unnamed_addr #0

declare zeroext i1 @_nd_CONFIG_LFS() local_unnamed_addr #0

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assume(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assume.not(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @seahorn.fail() #1

; Function Attrs: inaccessiblememonly nofree norecurse noreturn nounwind
declare void @verifier.error() #2

; Function Attrs: inaccessiblememonly
declare void @seahorn.fn.enter() local_unnamed_addr #3

declare i32 @verifier.nondet.0()

; Function Attrs: nounwind uwtable
define dso_local i32 @main() local_unnamed_addr #4 {
entry:
  call void @seahorn.fn.enter() #7
  %_0 = call i64 @_nd_st_size1() #7
  %_1 = call i64 @_nd_st_size2() #7
  %_2 = call i64 @_nd_st_atim_tv_sec1() #7
  %_3 = call i64 @_nd_st_atim_tv_sec2() #7
  %_4 = call i64 @_nd_st_ctim_tv_sec1() #7
  %_5 = call i64 @_nd_st_ctim_tv_sec2() #7
  %_6 = call i64 @_nd_st_mtim_tv_sec1() #7
  %_7 = call i64 @_nd_st_mtim_tv_sec2() #7
  %_8 = call i32 @_nd_st_mode1() #7
  %_9 = call i32 @_nd_st_mode2() #7
  %_10 = call i32 @_nd_strcoll12() #7
  %_11 = call i32 @_nd_strcmp12() #7
  %_12 = call i32 @_nd_all_fmt() #7
  %_13 = call zeroext i1 @_nd_sort_size() #7
  %_14 = call zeroext i1 @_nd_sort_atime() #7
  %_15 = call zeroext i1 @_nd_sort_ctime() #7
  %_16 = call zeroext i1 @_nd_sort_mtime() #7
  %_17 = call zeroext i1 @_nd_sort_dir() #7
  %_18 = call zeroext i1 @_nd_sort_reverse() #7
  %_19 = call zeroext i1 @_nd_CONFIG_LOCALE_SUPPORT() #7
  %_20 = call zeroext i1 @_nd_CONFIG_LFS() #7
  call void @verifier.assume(i1 %_20) #7
  %_21 = select i1 %_19, i1 %_20, i1 false
  %_22 = xor i1 %_21, true
  call void @verifier.assume(i1 %_22) #7
  %_23 = xor i1 %_19, true
  call void @verifier.assume(i1 %_23) #7
  %_24 = xor i1 %_19, true
  call void @verifier.assume(i1 %_24) #7
  %_25 = xor i1 %_19, true
  call void @verifier.assume(i1 %_25) #7
  %_26 = xor i1 %_19, true
  call void @verifier.assume(i1 %_26) #7
  %_27 = xor i1 %_19, true
  call void @verifier.assume(i1 %_27) #7
  %_28 = xor i1 %_19, true
  call void @verifier.assume(i1 %_28) #7
  %_29 = xor i1 %_19, true
  call void @verifier.assume(i1 %_29) #7
  %_30 = xor i1 %_19, true
  call void @verifier.assume(i1 %_30) #7
  %_31 = xor i1 %_19, true
  call void @verifier.assume(i1 %_31) #7
  %_32 = xor i1 %_19, true
  call void @verifier.assume(i1 %_32) #7
  %_33 = xor i1 %_19, true
  call void @verifier.assume(i1 %_33) #7
  %_34 = xor i1 %_19, true
  call void @verifier.assume(i1 %_34) #7
  %_35 = xor i1 %_19, true
  call void @verifier.assume(i1 %_35) #7
  %_36 = xor i1 %_19, true
  call void @verifier.assume(i1 %_36) #7
  %_37 = xor i1 %_19, true
  call void @verifier.assume(i1 %_37) #7
  %_38 = call i1 @nondet.bool()
  call void @verifier.assume.not(i1 %_38)
  br label %precall

precall:                                          ; preds = %entry
  br label %sortcmp

sortcmp:                                          ; preds = %precall
  call void @seahorn.fn.enter() #7
  br i1 %_13, label %_39, label %_42

_39:                                              ; preds = %sortcmp
  %_40 = add nsw i64 %_1, %_0
  %_41 = sub nsw i64 %_1, %_0
  br label %_68

_42:                                              ; preds = %sortcmp
  br i1 %_14, label %_43, label %_46

_43:                                              ; preds = %_42
  %_44 = add nsw i64 %_3, %_2
  %_45 = sub nsw i64 %_3, %_2
  br label %_68

_46:                                              ; preds = %_42
  br i1 %_15, label %_47, label %_50

_47:                                              ; preds = %_46
  %_48 = add nsw i64 %_5, %_4
  %_49 = sub nsw i64 %_5, %_4
  br label %_68

_50:                                              ; preds = %_46
  br i1 %_16, label %_51, label %_54

_51:                                              ; preds = %_50
  %_52 = sub nsw i64 %_7, %_6
  %_53 = sub nsw i64 %_7, %_6
  br label %_68

_54:                                              ; preds = %_50
  br i1 %_17, label %_55, label %_68

_55:                                              ; preds = %_54
  %_56 = and i32 %_9, 61440
  %_57 = icmp eq i32 %_56, 16384
  %_58 = zext i1 %_57 to i64
  %_59 = and i32 %_8, 61440
  %_60 = icmp eq i32 %_59, 16384
  %.neg.i = sext i1 %_60 to i64
  %_61 = add nsw i64 %.neg.i, %_58
  %_62 = and i32 %_9, 61440
  %_63 = icmp eq i32 %_62, 16384
  %_64 = zext i1 %_63 to i64
  %_65 = and i32 %_8, 61440
  %_66 = icmp eq i32 %_65, 16384
  %.neg8.i = sext i1 %_66 to i64
  %_67 = add nsw i64 %.neg8.i, %_64
  br label %_68

_68:                                              ; preds = %_55, %_54, %_51, %_47, %_43, %_39
  %.01.i = phi i64 [ %_40, %_39 ], [ %_44, %_43 ], [ %_48, %_47 ], [ %_52, %_51 ], [ %_61, %_55 ], [ 0, %_54 ]
  %.0.i = phi i64 [ %_41, %_39 ], [ %_45, %_43 ], [ %_49, %_47 ], [ %_53, %_51 ], [ %_67, %_55 ], [ 0, %_54 ]
  %_69 = icmp eq i64 %.01.i, 0
  %spec.select12.v.i = select i1 %_19, i32 %_10, i32 %_11
  %spec.select12.i = sext i32 %spec.select12.v.i to i64
  %.23.i = select i1 %_69, i64 %spec.select12.i, i64 %.01.i
  %_70 = icmp eq i64 %.0.i, 0
  %spec.select13.v.i = select i1 %_19, i32 %_10, i32 %_11
  %spec.select13.i = sext i32 %spec.select13.v.i to i64
  %.2.i = select i1 %_70, i64 %spec.select13.i, i64 %.0.i
  %.not.i = icmp eq i64 %.23.i, 0
  br i1 %.not.i, label %_74, label %_71

_71:                                              ; preds = %_68
  %.45.i = ashr i64 %.23.i, 32
  %_72 = srem i64 %.45.i, 2
  %_73 = add nsw i64 %.45.i, %_72
  br label %_74

_74:                                              ; preds = %_71, %_68
  %.56.i = phi i64 [ %_73, %_71 ], [ %.23.i, %_68 ]
  %.not7.i = icmp eq i64 %.2.i, 0
  br i1 %.not7.i, label %_79, label %_75

_75:                                              ; preds = %_74
  %_76 = ashr i64 %.2.i, 32
  %.4.i = select i1 %_20, i64 %_76, i64 0
  %_77 = srem i64 %.4.i, 2
  %_78 = add nsw i64 %.4.i, %_77
  br label %_79

_79:                                              ; preds = %_75, %_74
  %.5.i = phi i64 [ %_78, %_75 ], [ %.2.i, %_74 ]
  %_80 = trunc i64 %.56.i to i32
  %_81 = sub nsw i32 0, %_80
  %_82 = trunc i64 %.56.i to i32
  %_83 = select i1 %_18, i32 %_81, i32 %_82
  %_84 = trunc i64 %.5.i to i32
  %_85 = sub nsw i32 0, %_84
  %_86 = trunc i64 %.5.i to i32
  %_87 = select i1 %_18, i32 %_85, i32 %_86
  %_88 = icmp eq i32 %_83, %_87
  call void @verifier.assume.not(i1 %_88)
  br label %_89

_89:                                              ; preds = %_79
  br label %verifier.error

verifier.error:                                   ; preds = %_89
  call void @seahorn.fail()
  br label %verifier.error.split

verifier.error.split:                             ; preds = %verifier.error
  ret i32 42
}

declare i1 @nondet.bool()

declare i64 @verifier.nondet.sym.bound()

declare void @shadow.mem.load(i32, i32, i8*)

declare void @shadow.mem.trsfr.load(i32, i32, i8*)

declare i32 @shadow.mem.store(i32, i32, i8*)

declare i32 @shadow.mem.global.init(i32, i32, i8*)

declare i32 @shadow.mem.init(i32, i8*)

declare i32 @shadow.mem.arg.init(i32, i8*)

declare void @shadow.mem.arg.ref(i32, i32, i32, i8*)

declare i32 @shadow.mem.arg.mod(i32, i32, i32, i8*)

declare i32 @shadow.mem.arg.new(i32, i32, i32, i8*)

declare void @shadow.mem.in(i32, i32, i32, i8*)

declare void @shadow.mem.out(i32, i32, i32, i8*)

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assert(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @verifier.assert.not(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare i1 @sea.is_dereferenceable(i8* nocapture, i64) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.assert.if(i1, i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.synth.assume(i1) #1

; Function Attrs: inaccessiblememonly nofree norecurse nounwind
declare void @sea.synth.assert(i1) #1

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_modified(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.reset_modified(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_read(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.reset_read(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i1 @sea.is_alloc(i8* nocapture) #5

; Function Attrs: nofree norecurse nounwind
declare void @sea.tracking_on() #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.tracking_off() #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.free(i8* nocapture) #6

; Function Attrs: nofree norecurse nounwind
declare void @sea.set_shadowmem(i8, i8* nocapture, i8) #6

; Function Attrs: nofree norecurse nounwind readonly
declare i8 @sea.get_shadowmem(i8, i8* nocapture) #5

attributes #0 = { "frame-pointer"="none" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #1 = { inaccessiblememonly nofree norecurse nounwind }
attributes #2 = { inaccessiblememonly nofree norecurse noreturn nounwind }
attributes #3 = { inaccessiblememonly }
attributes #4 = { nounwind uwtable "frame-pointer"="none" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #5 = { nofree norecurse nounwind readonly }
attributes #6 = { nofree norecurse nounwind }
attributes #7 = { nounwind }

!llvm.module.flags = !{!0, !1, !2, !3}
!llvm.ident = !{!4}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"PIC Level", i32 2}
!2 = !{i32 7, !"PIE Level", i32 2}
!3 = !{i32 7, !"uwtable", i32 1}
!4 = !{!"Ubuntu clang version 14.0.0-1ubuntu1"}
